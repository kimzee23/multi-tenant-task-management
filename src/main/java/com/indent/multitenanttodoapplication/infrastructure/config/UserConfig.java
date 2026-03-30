package com.indent.multitenanttodoapplication.infrastructure.config;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.service.UserService;
import com.indent.multitenanttodoapplication.infrastructure.adapter.UserRepositoryAdapter;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository.SpringDataUserRepository;
import org.springframework.context.annotation.Bean;

public class UserConfig {

    @Bean
    public UserRepositoryPort userRepository(SpringDataUserRepository repo) {
        return new UserRepositoryAdapter(repo);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort repo) {
        return new UserService(repo);
    }
}
