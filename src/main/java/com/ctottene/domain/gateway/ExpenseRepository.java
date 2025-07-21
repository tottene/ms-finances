package com.ctottene.domain.gateway;

import com.ctottene.domain.model.Expense;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    Optional<Expense> findById(UUID id);
    List<Expense> findAllByTenantId(UUID tenantId);
    Expense save(Expense expense);
    void deleteById(UUID id);
}
