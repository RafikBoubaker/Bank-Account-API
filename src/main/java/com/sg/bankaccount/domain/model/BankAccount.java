package com.sg.bankaccount.domain.model;
import com.sg.bankaccount.domain.model.enumeration.TransactionType;
import com.sg.bankaccount.shared.exceptions.InsufficientFundsException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BankAccount {
    private String id;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public BankAccount(String id) {
        this.id = id;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }


    public void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        balance = balance.add(amount);
        transactions.add(new Transaction(LocalDateTime.now(), amount, TransactionType.DEPOSIT, balance));
    }




    public void withdraw(BigDecimal amount) {
        validatePositiveAmount(amount);
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Solde insuffisant pour le retrait");
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction(LocalDateTime.now(), amount, TransactionType.WITHDRAWAL, balance));
    }


    private void validatePositiveAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("le montant doit etre positif");
        }
    }



    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }


}