package com.indent.multitenanttodoapplication.infrastructure.config;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.input.GetUsersByTenantUseCase;
import com.indent.multitenanttodoapplication.application.ports.input.LoginUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.input.RegisterUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.service.GetUsersByTenantService;
import com.indent.multitenanttodoapplication.domain.service.LoginService;
import com.indent.multitenanttodoapplication.domain.service.RegisterService;
import com.indent.multitenanttodoapplication.domain.service.UserService;
import com.indent.multitenanttodoapplication.infrastructure.adapter.UserRepositoryAdapter;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository.SpringDataUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort repo) {
        return new UserService(repo);
    }
    @Bean
    public GetUsersByTenantUseCase getUsersByTenantUseCase(UserRepositoryPort repo) {
        return new GetUsersByTenantService(repo);
    }
    @Bean
    public LoginUserUseCase loginUserUseCase(UserRepositoryPort repo) {
        return new LoginService(repo);
    }
    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepositoryPort repo){
        return new RegisterService(repo);
    }
}
