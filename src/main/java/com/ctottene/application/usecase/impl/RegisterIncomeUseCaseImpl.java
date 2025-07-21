package com.ctottene.application.usecase.impl;

import com.ctottene.application.usecase.RegisterIncomeUseCase;
import com.ctottene.application.usecase.dto.RegisterIncomeInput;
import com.ctottene.application.usecase.dto.RegisterIncomeOutput;
import com.ctottene.domain.gateway.IncomeRepository;
import com.ctottene.domain.model.Income;
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
        income.setAmount(input.amount());
        income.setOriginalDate(input.originalDate());
        income.setDueDate(input.dueDate());
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
