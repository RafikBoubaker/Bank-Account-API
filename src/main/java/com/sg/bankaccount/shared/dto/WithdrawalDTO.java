package com.sg.bankaccount.shared.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record WithdrawalDTO(
        @Positive(message = "le montant du retrait doit etre positif")
        BigDecimal amount
) {}



