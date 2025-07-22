package com.ctottene.infrastructure.persistence.entity;

import com.ctottene.domain.model.Income;
import com.ctottene.infrastructure.persistence.entity.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "incomes")
public class IncomeEntity extends TransactionEntity {


    public IncomeEntity() {}

    public static IncomeEntity fromModel(Income income) {
        IncomeEntity entity = new IncomeEntity();
        entity.setId(income.getId());
        entity.setDescription(income.getDescription());
        entity.setAmount(income.getAmount());
        entity.setOriginalAmount(income.getOriginalAmount());
        entity.setInterest(income.getInterest());
        entity.setFine(income.getFine());
        entity.setDiscount(income.getDiscount());
        entity.setOriginalDate(income.getOriginalDate());
        entity.setDueDate(income.getDueDate());
        entity.setPaidAt(income.getPaidAt());

        entity.setCreatedAt(income.getCreatedAt());
        entity.setCreatedBy(income.getCreatedBy());
        entity.setUpdatedAt(income.getUpdatedAt());
        entity.setUpdatedBy(income.getUpdatedBy());
        entity.setTenantId(income.getTenantId());
        entity.setUserTimeZone(income.getUserTimeZone());

        if (income.getCategory() != null) {
            CategoryEntity cat = new CategoryEntity();
            cat.setId(income.getCategory().getId());
            entity.setCategory(cat);
        }

        return entity;
    }

    public Income toModel() {
        Income income = new Income();
        income.setId(getId());
        income.setDescription(getDescription());
        income.setAmount(getAmount());
        income.setOriginalAmount(getOriginalAmount());
        income.setInterest(getInterest());
        income.setFine(getFine());
        income.setDiscount(getDiscount());
        income.setOriginalDate(getOriginalDate());
        income.setDueDate(getDueDate());
        income.setPaidAt(getPaidAt());

        income.setCreatedAt(getCreatedAt());
        income.setCreatedBy(getCreatedBy());
        income.setUpdatedAt(getUpdatedAt());
        income.setUpdatedBy(getUpdatedBy());
        income.setTenantId(getTenantId());

        if (getCategory() != null) {
            income.setCategory(getCategory().toModel());
        }
        return income;
    }
}
