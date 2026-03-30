package com.indent.multitenanttodoapplication.domain.service;

import com.indent.multitenanttodoapplication.application.ports.output.TenantRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TenantServiceTest{
    @Mock
    private TenantRepositoryPort repository;

    private TenantService useCase;

    @BeforeEach
    void setUp() {
        useCase = new TenantService(repository);
    }

    @Test
    void shouldCreateTenantSuccessfully() {

        String name = "TestTenant";

        when(repository.existsByName(name)).thenReturn(false);
        when(repository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Tenant result = useCase.createTenant(name);

        assertEquals(name, result.getName());
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


