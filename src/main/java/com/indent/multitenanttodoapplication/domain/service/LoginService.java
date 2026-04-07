package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.input.LoginUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service

public class LoginService implements LoginUserUseCase {

    private final UserRepositoryPort repository;

    public LoginService(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public UserModel login(String tenantId, String email, String password) {
        UserModel user = repository.findByEmailAndTenantId(email, tenantId)
                .orElseThrow(() -> new ValidationException("Invalid credentials"));

        log.info("HASHED PASSWORD (FROM DB): {}", user.getPassword());
        log.info("RAW PASSWORD (INPUT): {}", password);
        log.info("MATCH RESULT: {}", PasswordUtil.matches(password, user.getPassword()));

        if (!PasswordUtil.matches(password, user.getPassword())) {
            throw new ValidationException("Invalid credentials");
        }

        return user;
    }
}