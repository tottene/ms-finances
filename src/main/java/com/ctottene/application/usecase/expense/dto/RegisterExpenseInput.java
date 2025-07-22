package com.ctottene.application.usecase.expense.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RegisterExpenseInput(
        String description,
        BigDecimal amount,
        BigDecimal originalAmount,
        BigDecimal interest,
        BigDecimal fine,
        BigDecimal discount,
        Instant originalDate,
        Instant dueDate,
        java.util.UUID categoryId
) {}
