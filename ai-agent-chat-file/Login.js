import axios from "axios";
import { useState } from "react";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await axios.post(`${process.env.REACT_APP_GATEWAY_URL}/api/v1/auth/login`, {
        username,
        password,
      });
      localStorage.setItem("token", response.data.token);
      alert("Login successful");
    } catch (err) {
      alert("Login failed");
    }
  };

  return (
    <div className="login">
      <input placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default Login;
