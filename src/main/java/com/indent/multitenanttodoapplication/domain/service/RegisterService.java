package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.input.RegisterUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.domain.validator.UserValidator;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
@Service
public class RegisterService implements RegisterUserUseCase {
    private final UserRepositoryPort repository;

    public RegisterService(UserRepositoryPort repository){
        this.repository =  repository;
    }
    @Override
    public UserModel register(String tenantId, String email, String password, String phoneNumber){
        if (repository.existsByEmailAndTenantId(email, tenantId)){
            throw new ValidationException("user already exits");
        }
        UserModel user = UserModel.builder()
                .id(UUID.randomUUID().toString())
                .tenantId(tenantId)
                .email(email)
                .password(PasswordUtil.hash(password))
                .phoneNumber(phoneNumber)
                .role(UserRole.USER)
                .createdAt(Instant.now().toString())
                .build();
        UserValidator.validate(user);
        return repository.save(user);
    }

}
