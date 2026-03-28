package com.indent.multitenanttodoapplication.application.ports.input;

import com.indent.multitenanttodoapplication.domain.model.Tenant;

public interface CreateTenantUseCase {
    Tenant createTenant(String name);
}
