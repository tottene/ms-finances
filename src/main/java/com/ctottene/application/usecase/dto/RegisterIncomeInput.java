package com.ctottene.application.usecase.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RegisterIncomeInput(
        String description,
        BigDecimal amount,
        Instant originalDate,
        Instant dueDate
) {}
