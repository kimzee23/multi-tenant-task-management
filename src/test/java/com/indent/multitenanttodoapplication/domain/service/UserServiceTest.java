package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.exception.ValidationException;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserRepositoryPort repository = mock(UserRepositoryPort.class);

    private final UserService useCase =
            new UserService(repository);

    private final GetUsersByTenantService getUsersByTenantUseCase =
            new GetUsersByTenantService( repository);

    @Test
    void shouldCreateUserSuccessfully() {

        when(repository.existsByEmailAndTenantId("test@mail.com", "tenant-1"))
                .thenReturn(false);

        when(repository.save(any())).thenAnswer(invocation -> {
            UserModel user = invocation.getArgument(0);
            user.setId("user-1");
            return user;
        });

        UserModel user = useCase.createUser(
                "tenant-1",
                "test@mail.com",


                UserRole.ADMIN
        );

        assertNotNull(user.getId());
        assertEquals("tenant-1", user.getTenantId());
        assertEquals("test@mail.com", user.getEmail());

        verify(repository).save(any());
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        when(repository.existsByEmailAndTenantId("test@mail.com", "tenant-1"))
                .thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                useCase.createUser("tenant-1", "test@mail.com", UserRole.ADMIN)
        );

        assertEquals("User already exists in this tenant", exception.getMessage());

        verify(repository, never()).save(any());
    }

    @Test
    void shouldReturnUsersForTenant() {

        List<UserModel> users = List.of(
                UserModel.builder()
                        .id("1")
                        .tenantId("tenant-123")
                        .email("user1@test.com")
                        .role(UserRole.USER)
                        .build(),
                UserModel.builder()
                        .id("2")
                        .tenantId("tenant-123")
                        .email("user2@test.com")
                        .role(UserRole.ADMIN)
                        .build()
        );

        when(repository.findByTenantId("tenant-123")).thenReturn(users);

        List<UserModel> result = getUsersByTenantUseCase.getUsersByTenant("tenant-123");

        assertEquals(2, result.size());
        verify(repository).findByTenantId("tenant-123");
    }

    @Test
    void shouldThrowExceptionWhenTenantIdIsNull() {

        assertThrows(ValidationException.class, () ->
                getUsersByTenantUseCase.getUsersByTenant(null)
        );
    }
    @Test
    void shouldFindUserByEmailAndTenant() {

        when(repository.findByEmailAndTenantId("test@mail.com", "tenant-1"))
                .thenReturn(Optional.of(UserModel.builder()
                        .id("1")
                        .email("test@mail.com")
                        .tenantId("tenant-1")
                        .role(UserRole.USER)
                        .build()));

        Optional<UserModel> user =
                repository.findByEmailAndTenantId("test@mail.com", "tenant-1");

        assertTrue(user.isPresent());
    }

}