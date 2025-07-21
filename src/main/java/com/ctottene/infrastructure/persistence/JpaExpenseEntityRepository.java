package com.ctottene.infrastructure.persistence;

import com.ctottene.infrastructure.persistence.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaExpenseEntityRepository extends JpaRepository<ExpenseEntity, UUID> {
    List<ExpenseEntity> findAllByTenantId(UUID tenantId);
}
