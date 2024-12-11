package com.sg.bankaccount.infrastructure.adapters;

import com.sg.bankaccount.domain.model.BankAccount;
import com.sg.bankaccount.domain.ports.output.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, BankAccount> accounts = new ConcurrentHashMap<>();

    @Override
    public BankAccount findAccountById(String accountId) {
        return accounts.computeIfAbsent(accountId, BankAccount::new);
    }

    @Override
    public void save(BankAccount account) {
        accounts.put(account.getId(), account);
    }
}
