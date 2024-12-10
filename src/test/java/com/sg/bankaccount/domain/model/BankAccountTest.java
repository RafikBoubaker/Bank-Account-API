package com.sg.bankaccount.domain.model;

import com.sg.bankaccount.shared.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Test
    void shouldInitializeWithZeroBalance() {
        assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    void shouldDepositPositiveAmount() {
        account.deposit(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(100), account.getBalance());
    }

    @Test
    void shouldThrowExceptionForNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BigDecimal.valueOf(-50)));
    }

    @Test
    void shouldWithdrawValidAmount() {
        account.deposit(BigDecimal.valueOf(100));
        account.withdraw(BigDecimal.valueOf(50));
        assertEquals(BigDecimal.valueOf(50), account.getBalance());
    }

    @Test
    void shouldThrowExceptionForInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(BigDecimal.valueOf(50)));
    }
}