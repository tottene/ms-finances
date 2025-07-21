package com.ctottene.infrastructure.persistence.repository;

import com.ctottene.domain.gateway.ExpenseRepository;
import com.ctottene.domain.model.Expense;
import com.ctottene.infrastructure.persistence.JpaExpenseEntityRepository;
import com.ctottene.infrastructure.persistence.entity.ExpenseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private final JpaExpenseEntityRepository jpaRepository;

    public ExpenseRepositoryImpl(JpaExpenseEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Expense> findById(UUID id) {
        return jpaRepository.findById(id).map(ExpenseEntity::toModel);
    }

    @Override
    public List<Expense> findAllByTenantId(UUID tenantId) {
        return jpaRepository.findAllByTenantId(tenantId).stream()
                .map(ExpenseEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Expense save(Expense income) {
        ExpenseEntity entity = toEntity(income);
        ExpenseEntity saved = jpaRepository.save(entity);
        return saved.toModel();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private ExpenseEntity toEntity(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(expense.getId());
        entity.setAmount(expense.getAmount());
        entity.setDescription(expense.getDescription());
        entity.setOriginalDate(expense.getOriginalDate());
        entity.setDueDate(expense.getDueDate());
        entity.setPaidAt(expense.getPaidAt());
        entity.setCreatedAt(expense.getCreatedAt());
        entity.setCreatedBy(expense.getCreatedBy());
        entity.setTenantId(expense.getTenantId());
        entity.setUserTimeZone(expense.getUserTimeZone());
        return entity;
    }
}
