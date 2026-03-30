package com.indent.multitenanttodoapplication.infrastructure.config;

import com.indent.multitenanttodoapplication.application.ports.input.CreateTenantUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.TenantRepositoryPort;
import com.indent.multitenanttodoapplication.application.usecase_implementation.CreateTenantUseCaseImpl;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository.SpringDataTenantRepository;
import com.indent.multitenanttodoapplication.infrastructure.adapter.TenantRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantBeanConfig {

    @Bean
    public TenantRepositoryPort tenantRepository(SpringDataTenantRepository repo) {
        return new TenantRepositoryAdapter(repo);
    }

    @Bean
    public CreateTenantUseCase createTenantUseCase(TenantRepositoryPort repo) {
        return new CreateTenantUseCaseImpl(repo);
    }
}