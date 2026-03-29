package com.indent.multitenanttodoapplication.application.ports.output;

import com.indent.multitenanttodoapplication.domain.model.Tenant;

public interface TenantRepositoryPort {
    Tenant save(Tenant tenant);

    boolean existsByName(String name);

}
