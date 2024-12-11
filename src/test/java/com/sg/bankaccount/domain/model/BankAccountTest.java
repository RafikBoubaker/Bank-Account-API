package com.sg.bankaccount.domain.model;

import com.sg.bankaccount.domain.model.enumeration.TransactionType;
import com.sg.bankaccount.shared.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private static final String TEST_ACCOUNT_ID = "test-id";

    @Test
    void shouldInitializeWithZeroBalance() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    void shouldHaveSpecifiedId() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        assertEquals(TEST_ACCOUNT_ID, account.getId());
    }

    @Test
    void shouldCreateDifferentAccountsWithDifferentIds() {
        BankAccount account1 = new BankAccount("account-1");
        BankAccount account2 = new BankAccount("account-2");
        assertNotEquals(account1.getId(), account2.getId());
    }

    @Test
    void shouldDepositPositiveAmount() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        account.deposit(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), account.getBalance());
    }

    @Test
    void shouldThrowExceptionForNegativeDeposit() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.valueOf(-50)));
    }

    @Test
    void shouldWithdrawValidAmount() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        account.deposit(BigDecimal.valueOf(100));
        account.withdraw(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }

    @Test
    void shouldThrowExceptionForInsufficientFunds() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(BigDecimal.valueOf(50)));
    }

    @Test
    void shouldStoreTransactionsWithCorrectDetails() {
        BankAccount account = new BankAccount(TEST_ACCOUNT_ID);
        account.deposit(BigDecimal.valueOf(100));

        assertEquals(1, account.getTransactions().size());
        Transaction transaction = account.getTransactions().get(0);

        assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
        assertEquals(TransactionType.DEPOSIT, transaction.getType());
        assertEquals(BigDecimal.valueOf(100), transaction.getBalance());
        assertNotNull(transaction.getDate());
    }
}