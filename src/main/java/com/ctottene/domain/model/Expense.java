package com.ctottene.domain.model;

import com.ctottene.domain.enums.TransactionType;

public class Expense extends Transaction {

    private TransactionType type = TransactionType.EXPENSE;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
