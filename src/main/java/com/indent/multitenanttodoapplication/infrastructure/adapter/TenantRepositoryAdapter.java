package com.indent.multitenanttodoapplication.infrastructure.adapter;

import com.indent.multitenanttodoapplication.application.ports.output.TenantRepositoryPort;
import com.indent.multitenanttodoapplication.domain.model.Tenant;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.entity.TenantEntity;
import com.indent.multitenanttodoapplication.infrastructure.adapter.output.persistence.repository.SpringDataTenantRepository;

public class TenantRepositoryAdapter implements TenantRepositoryPort {
     private final SpringDataTenantRepository repository;
     public TenantRepositoryAdapter(SpringDataTenantRepository repository){
         this.repository = repository;
     }
     @Override
    public Tenant save(Tenant tenant){
         TenantEntity doc = new TenantEntity();
         doc.setId(tenant.getId());
         doc.setName(tenant.getName());
         doc.setCreatedAt(tenant.getCreatedAt());

         TenantEntity saved = repository.save(doc);
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
