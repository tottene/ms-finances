package com.ctottene.infrastructure.persistence.entity;

import com.ctottene.domain.model.Income;
import com.ctottene.infrastructure.persistence.entity.CategoryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;

import java.util.UUID;

@Entity
@Table(name = "incomes")
public class IncomeEntity extends TransactionEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    public IncomeEntity() {}

    public static IncomeEntity fromModel(Income income) {
        IncomeEntity entity = new IncomeEntity();
        entity.setId(income.getId());
        entity.setDescription(income.getDescription());
        entity.setAmount(income.getAmount());
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
        income.setOriginalDate(getOriginalDate());
        income.setDueDate(getDueDate());
        income.setPaidAt(getPaidAt());

        income.setCreatedAt(getCreatedAt());
        income.setCreatedBy(getCreatedBy());
        income.setUpdatedAt(getUpdatedAt());
        income.setUpdatedBy(getUpdatedBy());
        income.setTenantId(getTenantId());

        if (category != null) {
            income.setCategory(category.toModel());
        }

        return income;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
