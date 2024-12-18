package com.sg.bankaccount.application.service;

import com.sg.bankaccount.domain.model.BankAccount;
import com.sg.bankaccount.domain.ports.input.AccountService;
import com.sg.bankaccount.domain.ports.output.AccountRepository;
import com.sg.bankaccount.shared.dto.DepositDTO;
import com.sg.bankaccount.shared.dto.WithdrawalDTO;
import com.sg.bankaccount.shared.dto.StatementEntryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deposit(DepositDTO depositDTO) {

        logger.info("Depot effectué de {} sur le compte {}", depositDTO.amount(), depositDTO.accountId());

        BankAccount account = accountRepository.findAccountById(depositDTO.accountId());
        account.deposit(depositDTO.amount());
        accountRepository.save(account);
    }

    @Override
    public void withdraw(WithdrawalDTO withdrawalDTO) {

        logger.info("Retrait effectué de {} du compte {}", withdrawalDTO.amount(), withdrawalDTO.accountId());

        BankAccount account = accountRepository.findAccountById(withdrawalDTO.accountId());
        account.withdraw(withdrawalDTO.amount());
        accountRepository.save(account);
    }

    @Override
    public List<StatementEntryDTO> getStatement(String accountId) {
        BankAccount account = accountRepository.findAccountById(accountId);
        return account.getTransactions().stream()
                .map(trx -> new StatementEntryDTO(
                        trx.getDate(),
                        trx.getAmount(),
                        trx.getType().name(),
                        trx.getBalance()
                ))
                .collect(Collectors.toList());
    }
}