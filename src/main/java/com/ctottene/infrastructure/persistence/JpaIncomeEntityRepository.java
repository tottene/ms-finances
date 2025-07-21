package com.ctottene.infrastructure.persistence;

import com.ctottene.infrastructure.persistence.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaIncomeEntityRepository extends JpaRepository<IncomeEntity, UUID> {
    List<IncomeEntity> findAllByTenantId(UUID tenantId);
}
