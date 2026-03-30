package com.indent.multitenanttodoapplication.infrastructure.adapter;

import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository.SpringDataUserRepository;

public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;

    public UserRepositoryAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserModel save(UserModel user) {

        UserEntity doc = new UserEntity();
        doc.setId(user.getId());
        doc.setTenantId(user.getTenantId());
        doc.setEmail(user.getEmail());
        doc.setRole(user.getRole().name());
        doc.setCreatedAt(user.getCreatedAt());

        repository.save(doc);

        return user;
    }

    @Override
    public boolean existsByEmailAndTenantId(String email, String tenantId) {
        return repository.existsByEmailAndTenantId(email, tenantId);
    }
}
