package com.example.prova.startup;

import com.example.prova.entity.AppUser;
import com.example.prova.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(AppUserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("pleno").isEmpty()) {
                repo.save(new AppUser("pleno", encoder.encode("123456"), "ROLE_USER"));
            }
        };
    }
}