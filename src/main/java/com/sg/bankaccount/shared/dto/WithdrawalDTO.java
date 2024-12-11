package com.sg.bankaccount.shared.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WithdrawalDTO(

        @NotNull(message = "L'ID du compte est obligatoire")
        String accountId,

        @Positive(message = "le montant du retrait doit etre positif")
        BigDecimal amount
) {}



