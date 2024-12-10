package com.sg.bankaccount.domain.model;

import com.sg.bankaccount.domain.model.enumeration.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime date;
    private final BigDecimal amount;
    private final TransactionType type;
    private final BigDecimal balance;

    public Transaction(LocalDateTime date, BigDecimal amount, TransactionType type, BigDecimal balance) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.balance = balance;
    }


    public LocalDateTime getDate() { return date; }
    public BigDecimal getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public BigDecimal getBalance() { return balance; }
}
