package com.ctottene.domain.gateway;

import com.ctottene.domain.model.Income;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncomeRepository {
    Optional<Income> findById(UUID id);
    List<Income> findAllByTenantId(UUID tenantId);
    Income save(Income income);
    void deleteById(UUID id);
}
