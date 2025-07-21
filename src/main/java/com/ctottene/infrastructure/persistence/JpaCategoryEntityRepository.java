package com.ctottene.infrastructure.persistence;

import com.ctottene.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaCategoryEntityRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findAllByTenantId(UUID tenantId);
}
