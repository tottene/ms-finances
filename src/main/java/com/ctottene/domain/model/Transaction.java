package com.ctottene.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


public abstract class Transaction extends AuditMetadata {
    protected UUID id;
    protected String description;
    protected BigDecimal amount;

    protected BigDecimal originalAmount;
    protected BigDecimal interest;
    protected BigDecimal fine;
    protected BigDecimal discount;

    protected Instant createdAt;
    protected Instant originalDate;
    protected Instant dueDate;
    protected Instant paidAt;

    protected Category category;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getOriginalAmount() { return originalAmount; }
    public void setOriginalAmount(BigDecimal originalAmount) { this.originalAmount = originalAmount; }

    public BigDecimal getInterest() { return interest; }
    public void setInterest(BigDecimal interest) { this.interest = interest; }

    public BigDecimal getFine() { return fine; }
    public void setFine(BigDecimal fine) { this.fine = fine; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getOriginalDate() { return originalDate; }
    public void setOriginalDate(Instant originalDate) { this.originalDate = originalDate; }

    public Instant getDueDate() { return dueDate; }
    public void setDueDate(Instant dueDate) { this.dueDate = dueDate; }

    public Instant getPaidAt() { return paidAt; }
    public void setPaidAt(Instant paidAt) { this.paidAt = paidAt; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
