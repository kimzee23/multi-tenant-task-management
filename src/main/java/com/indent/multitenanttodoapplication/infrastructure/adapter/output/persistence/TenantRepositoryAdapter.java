package com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence;

import com.indent.multitenanttodoapplication.application.ports.output.TenantRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.Tenant;

public class TenantRepositoryAdapter implements TenantRepositoryPort {
     private final SpringDataTenantRepository repository;
     public TenantRepositoryAdapter(SpringDataTenantRepository repository){
         this.repository = repository;
     }
     @Override
    public Tenant save(Tenant tenant){
         TenantDocument doc = new TenantDocument();
         doc.setId(tenant.getId());
         doc.setName(tenant.getName());
         doc.setCreatedAt(tenant.getCreatedAt());

         TenantDocument saved = repository.save(doc);
         return new Tenant(
                 saved.getId(),
                 saved.getName(),
                 saved.getCreatedAt()
         );
     }
     @Override
    public boolean existsByName(String name){
         return repository.existsByName(name);
     }
}
