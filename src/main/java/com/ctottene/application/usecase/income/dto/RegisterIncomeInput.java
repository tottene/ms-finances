package com.ctottene.application.usecase.income.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record RegisterIncomeInput(
        String description,
        BigDecimal amount,
        BigDecimal originalAmount,
        BigDecimal interest,
        BigDecimal fine,
        BigDecimal discount,
        Instant originalDate,
        Instant dueDate,
        UUID categoryId
) {}
