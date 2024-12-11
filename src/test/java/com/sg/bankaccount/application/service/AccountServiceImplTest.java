package com.sg.bankaccount.application.service;

import com.sg.bankaccount.domain.model.BankAccount;
import com.sg.bankaccount.domain.model.Transaction;
import com.sg.bankaccount.domain.model.enumeration.TransactionType;
import com.sg.bankaccount.domain.ports.output.AccountRepository;
import com.sg.bankaccount.shared.dto.DepositDTO;
import com.sg.bankaccount.shared.dto.StatementEntryDTO;
import com.sg.bankaccount.shared.dto.WithdrawalDTO;
import com.sg.bankaccount.shared.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountServiceImpl accountService;
    private BankAccount bankAccount;
    private String accountId;

    @BeforeEach
    void setUp() {
        accountId = "test-account-id";
        bankAccount = new BankAccount(accountId);
        when(accountRepository.findAccountById(accountId)).thenReturn(bankAccount);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    void shouldSuccessfullyDepositMoney() {
        DepositDTO depositDTO = new DepositDTO(accountId, BigDecimal.valueOf(100));

        accountService.deposit(depositDTO);

        verify(accountRepository).save(bankAccount);
        assertEquals(BigDecimal.valueOf(100), bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactions().size());

        Transaction lastTransaction = bankAccount.getTransactions().get(0);
        assertEquals(BigDecimal.valueOf(100), lastTransaction.getAmount());
        assertEquals(TransactionType.DEPOSIT, lastTransaction.getType());
    }

    @Test
    void shouldThrowExceptionWhenDepositingNegativeAmount() {
        DepositDTO depositDTO = new DepositDTO(accountId, BigDecimal.valueOf(-50));

        assertThrows(IllegalArgumentException.class, () -> {
            accountService.deposit(depositDTO);
        });
    }

    @Test
    void shouldSuccessfullyWithdrawMoney() {
        bankAccount.deposit(BigDecimal.valueOf(200));
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO(accountId, BigDecimal.valueOf(100));

        accountService.withdraw(withdrawalDTO);

        verify(accountRepository).save(bankAccount);
        assertEquals(BigDecimal.valueOf(100), bankAccount.getBalance());
        assertEquals(2, bankAccount.getTransactions().size());

        Transaction lastTransaction = bankAccount.getTransactions().get(1);
        assertEquals(BigDecimal.valueOf(100), lastTransaction.getAmount());
        assertEquals(TransactionType.WITHDRAWAL, lastTransaction.getType());
    }

    @Test
    void shouldThrowExceptionWhenWithdrawingMoreThanBalance() {
        bankAccount.deposit(BigDecimal.valueOf(50));
        WithdrawalDTO withdrawalDTO = new WithdrawalDTO(accountId, BigDecimal.valueOf(100));

        assertThrows(InsufficientFundsException.class, () -> {
            accountService.withdraw(withdrawalDTO);
        });
    }

    @Test
    void shouldGetAccountStatement() {
        bankAccount.deposit(BigDecimal.valueOf(100));
        bankAccount.withdraw(BigDecimal.valueOf(50));

        List<StatementEntryDTO> statement = accountService.getStatement(accountId);

        assertNotNull(statement);
        assertEquals(2, statement.size());

        StatementEntryDTO firstEntry = statement.get(0);
        assertEquals(BigDecimal.valueOf(100), firstEntry.amount());
        assertEquals(TransactionType.DEPOSIT.name(), firstEntry.type());
        assertEquals(BigDecimal.valueOf(100), firstEntry.balance());

        StatementEntryDTO secondEntry = statement.get(1);
        assertEquals(BigDecimal.valueOf(50), secondEntry.amount());
        assertEquals(TransactionType.WITHDRAWAL.name(), secondEntry.type());
        assertEquals(BigDecimal.valueOf(50), secondEntry.balance());
    }

    @Test
    void shouldCallRepositoryToFindAccountById() {
        List<StatementEntryDTO> statement = accountService.getStatement(accountId);

        verify(accountRepository).findAccountById(accountId);
        assertNotNull(statement);
    }
}