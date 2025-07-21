package com.ctottene.application.usecase.expense.impl;

import com.ctottene.application.usecase.expense.RegisterExpenseUseCase;
import com.ctottene.application.usecase.expense.dto.RegisterExpenseInput;
import com.ctottene.application.usecase.expense.dto.RegisterExpenseOutput;
import com.ctottene.domain.gateway.ExpenseRepository;
import com.ctottene.domain.model.Expense;
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

        Expense income = new Expense();
        income.setId(UUID.randomUUID());
        income.setDescription(input.description());
        income.setAmount(input.amount());
        income.setOriginalDate(input.originalDate());
        income.setDueDate(input.dueDate());
        income.setUserTimeZone(authenticatedUser.getTimezone());

        income.setCreatedAt(Instant.now());
        income.setUpdatedAt(Instant.now());
        income.setCreatedBy(authenticatedUser.getId());
        income.setTenantId(authenticatedUser.getTenantId());
        income.setUserTimeZone(authenticatedUser.getTimezone());

        log.info("Saving Expense: {}", income);
        Expense saved = expenseRepository.save(income);
        log.info("Saved successfully: {}", saved.getId());

        return new RegisterExpenseOutput(income.getId());
    }
}
