package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.controller;

import com.indent.multitenanttodoapplication.application.ports.input.CreateTenantUseCase;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request.TenantRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TenantControllerTest {

    private final CreateTenantUseCase useCase = mock(CreateTenantUseCase.class);

    private final TenantController controller =
            new TenantController(useCase);

    @Test
    void shouldCreateTenantSuccessfully() {

        TenantRequest request = new TenantRequest();
        request.setName("TestTenant");

        when(useCase.createTenant("TestTenant"))
                .thenReturn(Tenant.builder()
                        .id("1")
                        .name("TestTenant")
                        .createdAt("now")
                        .build());

        var response = controller.createTenant(request);

        assertEquals("TestTenant", response.getName());
        verify(useCase).createTenant("TestTenant");
    }
}