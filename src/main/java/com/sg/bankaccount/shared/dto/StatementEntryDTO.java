package com.sg.bankaccount.shared.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StatementEntryDTO(
        LocalDateTime date,
        BigDecimal amount,
        String type,
        BigDecimal balance
) {}
