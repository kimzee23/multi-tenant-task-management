package com.indent.multitenanttodoapplication.infrastructure.adapter;

import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.entity.UserEntity;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;

//    public UserRepositoryAdapter(SpringDataUserRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public UserModel save(UserModel user) {

        System.out.println("🔥 USING UPDATED ADAPTER SAVE METHOD");

        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setTenantId(user.getTenantId());
        entity.setEmail(user.getEmail());
        entity.setRole(user.getRole().name());
        entity.setPassword(user.getPassword());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setCreatedAt(user.getCreatedAt());

        System.out.println("Saving user: " + user.getEmail() + " / password: " + user.getPassword());
        repository.save(entity);
        System.out.println("ADAPTER SAVE CALLED ");
        return user;
    }

    @Override
    public boolean existsByEmailAndTenantId(String email, String tenantId) {
        return repository.existsByEmailAndTenantId(email, tenantId);
    }


    @Override
    public List<UserModel> findByTenantId(String tenantId) {
        return repository.findByTenantId(tenantId)
                .stream()
                .map(entity -> UserModel.builder()
                        .id(entity.getId())
                        .tenantId(entity.getTenantId())
                        .email(entity.getEmail())
                        .password(entity.getPassword())
                        .phoneNumber(entity.getPhoneNumber())
                        .role(UserRole.valueOf(entity.getRole()))
                        .createdAt(entity.getCreatedAt())
                        .build())
                .toList();

    }
    @Override
    public Optional<UserModel> findByEmailAndTenantId(String email, String tenantId) {
        return repository.findByEmailAndTenantId(email, tenantId)
                .map(entity -> UserModel.builder()
                        .id(entity.getId())
                        .tenantId(entity.getTenantId())
                        .email(entity.getEmail())
                        .password(entity.getPassword())
                        .phoneNumber(entity.getPhoneNumber())
                        .role(UserRole.valueOf(entity.getRole()))
                        .createdAt(entity.getCreatedAt())
                        .build());
    }
}



