package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.input.GetUsersByTenantUseCase;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.UserRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private final CreateUserUseCase useCase = mock(CreateUserUseCase.class);
    private final GetUsersByTenantUseCase getUsersByTenantUseCase = mock(GetUsersByTenantUseCase.class);

    private final UserController controller =
            new UserController(useCase,getUsersByTenantUseCase);

    @Test
    void shouldCreateUserSuccessfully() {

     UserRequest request = new UserRequest();
        request.setEmail("test@mail.com");
        request.setRole("ADMIN");

        when(useCase.createUser(any(), any(), any()))
                .thenReturn(UserModel.builder()
                        .id("1")
                        .tenantId("tenant-1")
                        .email("test@mail.com")
                        .role(UserRole.ADMIN)
                        .createdAt("now")
                        .build());

        var response = controller.createUser("tenant-1", request);

        assertEquals("test@mail.com", response.getEmail());
        assertEquals("ADMIN", response.getRole());

        verify(useCase).createUser("tenant-1", "test@mail.com", UserRole.ADMIN);
    }

    @Test
    void shouldReturnUsersForTenant() {

        List<UserModel> users = List.of(
                UserModel.builder()
                        .id("1")
                        .tenantId("tenant-123")
                        .email("user@test.com")
                        .role(UserRole.USER)
                        .build()
        );

        when(getUsersByTenantUseCase.getUsersByTenant("tenant-123")).thenReturn(users);

        var response = controller.getUsersByTenant("tenant-123");

        assertEquals(1, response.size());
        assertEquals("user@test.com", response.get(0).getEmail());

        verify(getUsersByTenantUseCase).getUsersByTenant("tenant-123");
    }

}