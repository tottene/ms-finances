package com.ctottene.application.usecase.income.impl;

import com.ctottene.application.usecase.income.RegisterIncomeUseCase;
import com.ctottene.application.usecase.income.dto.RegisterIncomeInput;
import com.ctottene.application.usecase.income.dto.RegisterIncomeOutput;
import com.ctottene.domain.gateway.IncomeRepository;
import com.ctottene.domain.model.Income;
import com.ctottene.domain.model.Category;
import com.ctottene.infrastructure.security.AuthenticatedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RegisterIncomeUseCaseImpl implements RegisterIncomeUseCase {

    private final Logger log = LoggerFactory.getLogger(RegisterIncomeUseCaseImpl.class);

    private final IncomeRepository incomeRepository;
    private final AuthenticatedUser authenticatedUser;

    public RegisterIncomeUseCaseImpl(
            IncomeRepository incomeRepository,
            AuthenticatedUser authenticatedUser
    ) {
        this.incomeRepository = incomeRepository;
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public RegisterIncomeOutput execute(RegisterIncomeInput input) {

        Income income = new Income();
        income.setId(UUID.randomUUID());
        income.setDescription(input.description());

        java.math.BigDecimal originalAmount = input.originalAmount() != null ? input.originalAmount() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal interest = input.interest() != null ? input.interest() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal fine = input.fine() != null ? input.fine() : java.math.BigDecimal.ZERO;
        java.math.BigDecimal discount = input.discount() != null ? input.discount() : java.math.BigDecimal.ZERO;

        java.math.BigDecimal amount = originalAmount.add(interest).add(fine).subtract(discount);

        income.setOriginalAmount(originalAmount);
        income.setInterest(interest);
        income.setFine(fine);
        income.setDiscount(discount);
        income.setAmount(amount);
        income.setOriginalDate(input.originalDate());
        income.setDueDate(input.dueDate());
        if (input.categoryId() != null) {
            Category category = new Category();
            category.setId(input.categoryId());
            income.setCategory(category);
        }
        income.setUserTimeZone(authenticatedUser.getTimezone());

        income.setCreatedAt(Instant.now());
        income.setUpdatedAt(Instant.now());
        income.setCreatedBy(authenticatedUser.getId());
        income.setTenantId(authenticatedUser.getTenantId());
        income.setUserTimeZone(authenticatedUser.getTimezone());

        log.info("Salving Income: {}", income);
        Income saved = incomeRepository.save(income);
        log.info("Saved successfully: {}", saved.getId());

        return new RegisterIncomeOutput(income.getId());
    }
}
