package com.example.airline_api.entity;

import com.example.airline_api.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (appUserRepository.findByUsername("testuser").isEmpty()) {
            AppUser user = new AppUser();
            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("testpass"));
            appUserRepository.save(user);
            System.out.println("A test user created");
        }
    }
}

