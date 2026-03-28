package com.indent.multitenanttodoapplication.application.usecase;

import com.indent.multitenanttodoapplication.application.ports.input.CreateTenantUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.TenantRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import com.indent.multitenanttodoapplication.domain.validator.TenantValidator;

import java.time.Instant;
import java.util.UUID;

public class CreateTenantUseCaseImpl implements CreateTenantUseCase  {

    private final TenantRepositoryPort repository;

    public CreateTenantUseCaseImpl(TenantRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Tenant createTenant(String name) {

        Tenant tenant = Tenant.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .createdAt(Instant.now().toString())
                .build();

        TenantValidator.validate(tenant);

        return repository.save(tenant);
    }
}