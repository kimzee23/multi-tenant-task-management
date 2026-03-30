package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.output.UserRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserRepositoryPort repository = mock(UserRepositoryPort.class);

    private final UserService useCase =
            new UserService(repository);

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

}