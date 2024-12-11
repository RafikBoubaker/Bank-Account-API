package com.sg.bankaccount.shared.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositDTO(

        @NotBlank(message = "L'ID du compte est obligatoire")
        String accountId,

        @Positive(message = "le montant du depot doit etre positif")
        BigDecimal amount
) {}
