package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository;

import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataUserRepository extends MongoRepository<UserEntity, String> {
    boolean existsByEmailAndTenantId(String email, String tenantId);

    List<UserEntity> findByTenantId(String tenantId);
}
