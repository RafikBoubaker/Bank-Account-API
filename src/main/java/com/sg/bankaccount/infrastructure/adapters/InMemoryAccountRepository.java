package com.sg.bankaccount.infrastructure.adapters;

import com.sg.bankaccount.domain.model.BankAccount;
import com.sg.bankaccount.domain.ports.output.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private BankAccount account = new BankAccount();

    @Override
    public BankAccount findAccount() {
        return account;
    }


    @Override
    public void save(BankAccount account) {
        this.account = account;
    }
}

