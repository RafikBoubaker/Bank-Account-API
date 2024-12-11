package com.sg.bankaccount.domain.ports.input;

import com.sg.bankaccount.shared.dto.DepositDTO;
import com.sg.bankaccount.shared.dto.WithdrawalDTO;
import com.sg.bankaccount.shared.dto.StatementEntryDTO;

import java.util.List;

public interface AccountService {

    void deposit(DepositDTO depositDTO);
    void withdraw(WithdrawalDTO withdrawalDTO);
    List<StatementEntryDTO> getStatement(String accountId);

}