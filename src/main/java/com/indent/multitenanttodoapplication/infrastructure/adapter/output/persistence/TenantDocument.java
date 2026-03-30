package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tenants")
public class TenantDocument {
    @Id
    private String id;
    private String name;
    private String createdAt;
}
