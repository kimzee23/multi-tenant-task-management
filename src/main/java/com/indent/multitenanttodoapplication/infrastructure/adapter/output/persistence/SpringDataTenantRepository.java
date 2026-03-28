package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataTenantRepository
        extends MongoRepository<TenantDocument, String> {
}
