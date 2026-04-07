package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.input.RegisterUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.domain.validator.UserValidator;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Slf4j
@Service
public class RegisterService implements RegisterUserUseCase {

    private final UserRepositoryPort repository;

    public RegisterService(UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public UserModel register(String tenantId, String email, String password, String phoneNumber) {
        if (repository.existsByEmailAndTenantId(email, tenantId)) {
            throw new ValidationException("User already exists");
        }

        String hashedPassword = PasswordUtil.hash(password);
        log.info("RAW PASSWORD: {}", password);
        log.info("HASHED PASSWORD: {}", hashedPassword);

        UserModel user = UserModel.builder()
                .id(UUID.randomUUID().toString())
                .tenantId(tenantId)
                .email(email)
                .password(hashedPassword)
                .phoneNumber(phoneNumber)
                .role(UserRole.USER)
                .createdAt(Instant.now().toString())
                .build();

        return repository.save(user);
    }
}
