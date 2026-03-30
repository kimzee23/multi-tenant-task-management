package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository;

import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.entity.TenantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataTenantRepository
        extends MongoRepository<TenantEntity, String> {
    boolean existsByName(String name);
}
