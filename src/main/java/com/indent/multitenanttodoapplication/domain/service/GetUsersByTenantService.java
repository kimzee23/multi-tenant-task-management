package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.input.GetUsersByTenantUseCase;
import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GetUsersByTenantService  implements GetUsersByTenantUseCase {
    private final UserRepositoryPort repository;

    public GetUsersByTenantService(UserRepositoryPort repository){
        this.repository= repository;
    }
    @Override
    public List<UserModel> getUsersByTenant(String tenantId){
        if (tenantId == null || tenantId.isBlank()) {
            throw new ValidationException("Tenant is required");
        }
        return repository.findByTenantId(tenantId);
    }
}
