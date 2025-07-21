package com.ctottene.domain.model;

import com.ctottene.domain.enums.TransactionType;

/**
 * Represents an income transaction. Each income belongs to a {@link Category}.
 */

public class Income extends Transaction {

    private TransactionType type = TransactionType.INCOME;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

}
