package com.sg.bankaccount.domain.ports.output;

import com.sg.bankaccount.domain.model.BankAccount;
import org.springframework.stereotype.Repository;


public interface AccountRepository {

    void save(BankAccount account);

    BankAccount findAccountById(String accountId);

}
