package com.indent.multitenanttodoapplication.application.usecase;

import com.indent.multitenanttodoapplication.application.ports.output.TenantRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTenantUseCaseImplTest {

    private final TenantRepositoryPort repository = mock(TenantRepositoryPort.class);

    private final CreateTenantUseCaseImpl useCase =
            new CreateTenantUseCaseImpl(repository);

    @Test
    void shouldCreateTenantSuccessfully() {

        when(repository.existsByName("TestTenant")).thenReturn(false);

        Tenant tenant = useCase.createTenant("TestTenant");

        assertNotNull(tenant.getId());
        assertEquals("TestTenant", tenant.getName());

        verify(repository).save(any());
    }

    @Test
    void shouldThrowExceptionWhenTenantAlreadyExists() {

        when(repository.existsByName("TestTenant")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                useCase.createTenant("TestTenant")
        );

        assertEquals("Tenant with this name already exists", exception.getMessage());

        verify(repository, never()).save(any());
    }
}
