package com.restics.socialmedia;

import com.restics.socialmedia.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.logging.*;

@SpringBootApplication
public class SocialmediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialmediaApplication.class, args);
    }

//    @Bean
//    CommandLineRunner demo(UserRepository userRepo) {
//        return args -> {
//            System.out.println("\n=== USERS IN DATABASE ===");
//            userRepo.findAll().forEach(user ->
//                    System.out.println("  " + user.name() + " — " + user.bio())
//            );
//            System.out.println("=========================\n");
//        };
//    }
}