package com.indent.multitenanttodoapplication.infrastructure.adapter.input.rest.data.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTenantRequest {
    private String name;
}
