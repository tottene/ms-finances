package com.ctottene.infrastructure.persistence.repository;

import com.ctottene.domain.gateway.IncomeRepository;
import com.ctottene.domain.model.Income;
import com.ctottene.infrastructure.persistence.JpaIncomeEntityRepository;
import com.ctottene.infrastructure.persistence.entity.IncomeEntity;
import com.ctottene.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class IncomeRepositoryImpl implements IncomeRepository {

    private final JpaIncomeEntityRepository jpaRepository;

    public IncomeRepositoryImpl(JpaIncomeEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Income> findById(UUID id) {
        return jpaRepository.findById(id).map(IncomeEntity::toModel);
    }

    @Override
    public List<Income> findAllByTenantId(UUID tenantId) {
        return jpaRepository.findAllByTenantId(tenantId).stream()
                .map(IncomeEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Income save(Income income) {
        IncomeEntity entity = toEntity(income);
        IncomeEntity saved = jpaRepository.save(entity);
        return saved.toModel();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private IncomeEntity toEntity(Income income) {
        IncomeEntity entity = new IncomeEntity();
        entity.setId(income.getId());
        entity.setAmount(income.getAmount());
        entity.setDescription(income.getDescription());
        entity.setOriginalDate(income.getOriginalDate());
        entity.setDueDate(income.getDueDate());
        entity.setPaidAt(income.getPaidAt());
        entity.setCreatedAt(income.getCreatedAt());
        entity.setCreatedBy(income.getCreatedBy());
        entity.setTenantId(income.getTenantId());
        entity.setUserTimeZone(income.getUserTimeZone());
        if (income.getCategory() != null) {
            CategoryEntity cat = new CategoryEntity();
            cat.setId(income.getCategory().getId());
            entity.setCategory(cat);
        }
        return entity;
    }
}
