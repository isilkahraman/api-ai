import { useState } from "react";
import axios from "axios";
import "./App.css";

const Chat = () => {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState([]);
  const [isTyping, setIsTyping] = useState(false);

  const getTime = () => {
    const now = new Date();
    return now.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" });
  };

  const sendMessage = async () => {
    if (!input.trim()) return;

    const userMessage = {
      sender: "user",
      text: input,
      time: getTime(),
    };

    setMessages((prev) => [...prev, userMessage]);
    setIsTyping(true);

    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_URL}/api/chat`,
        { message: input }
      );

      const botMessage = {
        sender: "bot",
        text: response.data.result,
        time: getTime(),
      };

      setTimeout(() => {
        setMessages((prev) => [...prev, botMessage]);
        setIsTyping(false);
      }, 1000); 
    } catch (err) {
      const errorMsg = {
        sender: "bot",
        text: "Hata: " + (err.response?.data?.message || err.message),
        time: getTime(),
      };
      setMessages((prev) => [...prev, errorMsg]);
      setIsTyping(false);
    }

    setInput("");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    sendMessage();
  };

  const clearChat = () => {
    setMessages([]);
    setIsTyping(false);
  };

  return (
    <div className="chat-container">
      <h2>AI Chat Assistant</h2>

      <div className="chat-box">
        {messages.length === 0 && (
          <div className="empty-message">No message yet.</div>
        )}

        {messages.map((msg, index) => (
          <div
            key={index}
            className={`message ${msg.sender === "user" ? "user" : "bot"}`}
          >
            <div className="meta">
              {msg.sender === "user" ? "You" : "AI Chat Agent"} • {msg.time}
            </div>
            <div className="text">{msg.text}</div>
          </div>
        ))}

        {isTyping && (
          <div className="message bot typing">
            <div className="meta">AI Chat Agent typing...</div>
            <div className="dots">
              <span>.</span>
              <span>.</span>
              <span>.</span>
            </div>
          </div>
        )}
      </div>

      <form className="chat-input" onSubmit={handleSubmit}>
        <input
          type="text"
          value={input}
          placeholder="Write a message..."
          onChange={(e) => setInput(e.target.value)}
        />
        <button type="submit">Gönder</button>
        <button type="button" className="clear-btn" onClick={clearChat}>
          Clear chat
        </button>
      </form>
    </div>
  );
};

export default Chat;
