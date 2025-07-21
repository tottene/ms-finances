package com.ctottene.application.usecase.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RegisterExpenseInput(
        String description,
        BigDecimal amount,
        Instant originalDate,
        Instant dueDate
) {}
