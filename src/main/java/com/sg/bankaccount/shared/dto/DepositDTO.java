package com.sg.bankaccount.shared.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositDTO(
        @Positive(message = "le montant du depot doit etre positif")
        BigDecimal amount
) {}
