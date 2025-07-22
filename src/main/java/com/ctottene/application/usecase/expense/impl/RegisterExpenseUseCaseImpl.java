package com.ctottene.application.usecase.expense.impl;

import com.ctottene.application.usecase.expense.RegisterExpenseUseCase;
import com.ctottene.application.usecase.expense.dto.RegisterExpenseInput;
import com.ctottene.application.usecase.expense.dto.RegisterExpenseOutput;
import com.ctottene.domain.gateway.ExpenseRepository;
import com.ctottene.domain.model.Expense;
import com.ctottene.domain.model.Category;
import com.ctottene.infrastructure.security.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RegisterExpenseUseCaseImpl implements RegisterExpenseUseCase {

    private final Logger log = LoggerFactory.getLogger(RegisterExpenseUseCaseImpl.class);

    private final ExpenseRepository expenseRepository;
    private final AuthenticatedUser authenticatedUser;

    public RegisterExpenseUseCaseImpl(
            ExpenseRepository expenseRepository,
            AuthenticatedUser authenticatedUser
    ) {
        this.expenseRepository = expenseRepository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public RegisterExpenseOutput execute(RegisterExpenseInput input) {

        Expense expense = new Expense();
        expense.setId(UUID.randomUUID());
        expense.setDescription(input.description());

        java.math.BigDecimal originalAmount = input.originalAmount() != null ? input.originalAmount() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal interest = input.interest() != null ? input.interest() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal fine = input.fine() != null ? input.fine() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal discount = input.discount() != null ? input.discount() : java.math.BigDecimal.ZERO;

        java.math.BigDecimal amount = originalAmount.add(interest).add(fine).subtract(discount);

        expense.setOriginalAmount(originalAmount);
        expense.setInterest(interest);
        expense.setFine(fine);
        expense.setDiscount(discount);
        expense.setAmount(amount);
        expense.setOriginalDate(input.originalDate());
        expense.setDueDate(input.dueDate());
        if (input.categoryId() != null) {
            Category category = new Category();
            category.setId(input.categoryId());
            expense.setCategory(category);
        }
        expense.setUserTimeZone(authenticatedUser.getTimezone());

        expense.setCreatedAt(Instant.now());
        expense.setUpdatedAt(Instant.now());
        expense.setCreatedBy(authenticatedUser.getId());
        expense.setTenantId(authenticatedUser.getTenantId());
        expense.setUserTimeZone(authenticatedUser.getTimezone());

        log.info("Saving Expense: {}", expense);
        Expense saved = expenseRepository.save(expense);
        log.info("Saved successfully: {}", saved.getId());

        return new RegisterExpenseOutput(expense.getId());
    }
}
