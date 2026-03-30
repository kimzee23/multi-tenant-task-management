package com.indent.multitenanttodoapplication.application.usecase_implementation;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.domain.validator.UserValidator;

import java.time.Instant;
import java.util.UUID;

public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepositoryPort repository;

    public CreateUserUseCaseImpl(UserRepositoryPort repository){
        this.repository = repository;
    }
    @Override
    public UserModel createUser(String tenantId, String email, UserRole role) {

        if (repository.existsByEmailAndTenantId(email, tenantId)) {
            throw new RuntimeException("User already exists in this tenant");
        }

        UserModel user = UserModel.builder()
                .id(UUID.randomUUID().toString())
                .tenantId(tenantId)
                .email(email)
                .role(role)
                .createdAt(Instant.now().toString())
                .build();

        UserValidator.validate(user);

        return repository.save(user);
    }
}
