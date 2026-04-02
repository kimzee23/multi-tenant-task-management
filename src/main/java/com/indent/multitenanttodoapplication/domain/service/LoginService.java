package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.input.LoginUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginUserUseCase {

    private final UserRepositoryPort repository;


    public LoginService(UserRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    public UserModel login(String tenantId, String email, String password){
        UserModel user = repository.findByEmailAndTenantId(email,
                tenantId).orElseThrow(() -> new RuntimeException("User not found"));

        if (!PasswordUtil.matches(password, user.getPassword())) {
            throw new ValidationException("Invalid credentials");
        }

        return user;
    }
}
