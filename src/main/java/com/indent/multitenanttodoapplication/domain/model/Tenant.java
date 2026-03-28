package com.indent.multitenanttodoapplication.domain.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    private String id;
    private String name;
    private String createdAt;

}
