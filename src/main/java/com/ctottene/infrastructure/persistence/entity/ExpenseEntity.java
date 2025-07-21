package com.ctottene.infrastructure.persistence.entity;

import com.ctottene.domain.model.Expense;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenses")
public class ExpenseEntity extends TransactionEntity {

    public ExpenseEntity() {}

    public static ExpenseEntity fromModel(Expense expense) {
        ExpenseEntity entity = new ExpenseEntity();
        entity.setId(expense.getId());
        entity.setDescription(expense.getDescription());
        entity.setAmount(expense.getAmount());
        entity.setOriginalDate(expense.getOriginalDate());
        entity.setDueDate(expense.getDueDate());
        entity.setPaidAt(expense.getPaidAt());

        entity.setCreatedAt(expense.getCreatedAt());
        entity.setCreatedBy(expense.getCreatedBy());
        entity.setUpdatedAt(expense.getUpdatedAt());
        entity.setUpdatedBy(expense.getUpdatedBy());
        entity.setTenantId(expense.getTenantId());

        return entity;
    }

    public Expense toModel() {
        Expense expense = new Expense();
        expense.setId(getId());
        expense.setDescription(getDescription());
        expense.setAmount(getAmount());
        expense.setOriginalDate(getOriginalDate());
        expense.setDueDate(getDueDate());
        expense.setPaidAt(getPaidAt());

        expense.setCreatedAt(getCreatedAt());
        expense.setCreatedBy(getCreatedBy());
        expense.setUpdatedAt(getUpdatedAt());
        expense.setUpdatedBy(getUpdatedBy());
        expense.setTenantId(getTenantId());

        return expense;
    }
}
