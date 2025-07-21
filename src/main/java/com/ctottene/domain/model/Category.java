package com.ctottene.domain.model;

import java.util.List;
import java.util.UUID;

/**
 * Category entity used to group transactions.
 */

public class Category extends AuditMetadata {
    private UUID id;
    private String name;
    private String description;
    private List<Income> incomes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }
}
