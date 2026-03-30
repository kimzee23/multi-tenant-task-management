package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.input.CreateTenantUseCase;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.TenantRequest;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.TenantResponse;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.mapper.TenantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/tenants")
public class TenantController {

    private final CreateTenantUseCase useCase;

    public TenantController(CreateTenantUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public TenantResponse createTenant(@RequestBody TenantRequest request) {
        Tenant tenant = useCase.createTenant(request.getName());
        return TenantMapper.toResponse(tenant);
    }
}
