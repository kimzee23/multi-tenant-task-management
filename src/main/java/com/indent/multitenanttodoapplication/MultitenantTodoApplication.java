package com.indent.multitenanttodoapplication;

import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MultitenantTodoApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(MultitenantTodoApplication.class);

        app.setAdditionalProfiles("dev");
        app.setBannerMode(Banner.Mode.CONSOLE);

        app.setLogStartupInfo(true);

        app.run(args);
    }
    @Bean
    public CommandLineRunner init(UserRepositoryPort userRepository) {
        return args -> {
            System.out.println(" =========================== Application started successfully build with no Joy intend ===================================");
        };
    }
}

