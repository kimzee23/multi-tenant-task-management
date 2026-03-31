package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.input.CreateUserUseCase;
import com.indent.multitenanttodoapplication.application.ports.input.GetUsersByTenantUseCase;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import com.indent.multitenanttodoapplication.domain.model.UserModel;
import com.indent.multitenanttodoapplication.domain.model.enumType.UserRole;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.UserRequest;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.response.UserResponse;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.mapper.UserMapper;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.util.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final CreateUserUseCase useCase;
    private final GetUsersByTenantUseCase getUsersByTenantUseCase;

    public UserController(CreateUserUseCase useCase,
                          GetUsersByTenantUseCase getUsersByTenantUseCase) {
        this.useCase = useCase;
        this.getUsersByTenantUseCase = getUsersByTenantUseCase;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {

        String tenantId = TenantContext.getTenantId();

        UserModel user = useCase.createUser(
                tenantId,
                request.getEmail(),
                UserRole.valueOf(request.getRole())
        );

        return UserMapper.toResponse(user);
    }

    @GetMapping
    public List<UserResponse> getUsersByTenant() {

        String tenantId = TenantContext.getTenantId();

        return getUsersByTenantUseCase.getUsersByTenant(tenantId)
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }
}
