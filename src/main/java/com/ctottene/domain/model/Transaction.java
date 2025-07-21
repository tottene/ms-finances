package com.ctottene.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public abstract class Transaction extends AuditMetadata {
    protected UUID id;
    protected String description;
    protected BigDecimal amount;

    protected Instant createdAt;
    protected Instant originalDate;
    protected Instant dueDate;
    protected Instant paidAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getOriginalDate() { return originalDate; }
    public void setOriginalDate(Instant originalDate) { this.originalDate = originalDate; }

    public Instant getDueDate() { return dueDate; }
    public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }

    public Instant getPaidAt() { return paidAt; }
    public void setPaidAt(Instant paidAt) { this.paidAt = paidAt; }
}
