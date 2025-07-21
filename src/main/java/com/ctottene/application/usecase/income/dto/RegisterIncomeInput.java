package com.ctottene.application.usecase.income.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record RegisterIncomeInput(
        String description,
        BigDecimal amount,
        Instant originalDate,
        Instant dueDate,
        UUID categoryId
) {}
