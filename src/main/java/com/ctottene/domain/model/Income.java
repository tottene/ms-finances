package com.ctottene.domain.model;

import com.ctottene.domain.enums.TransactionType;
import com.ctottene.domain.model.Category;

/**
 * Represents an income transaction. Each income belongs to a {@link Category}.
 */

public class Income extends Transaction {

    private TransactionType type = TransactionType.INCOME;
    private Category category;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
