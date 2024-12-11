package com.sg.bankaccount.infrastructure.rest;

import com.sg.bankaccount.domain.ports.input.AccountService;
import com.sg.bankaccount.shared.dto.DepositDTO;
import com.sg.bankaccount.shared.dto.StatementEntryDTO;
import com.sg.bankaccount.shared.dto.WithdrawalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/deposit")
    @Operation(summary = "Effectuer un depot")
    @ApiResponse(responseCode = "200", description = "Depot réussi")
    @ApiResponse(responseCode = "400", description = "Montant invalide ou compte inexistant")
    public ResponseEntity<Void> deposit(@Valid @RequestBody DepositDTO depositDTO) {

        accountService.deposit(depositDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @PostMapping("/withdraw")
    @Operation(summary = "Effectuer un retrait")
    @ApiResponse(responseCode = "200", description = "Retrait réussi")
    @ApiResponse(responseCode = "400", description = "Montant invalide ou solde insuffisant")
    public ResponseEntity<Void> withdraw(@Valid @RequestBody WithdrawalDTO withdrawalDTO) {
        accountService.withdraw(withdrawalDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @GetMapping("/statement/{accountId}")
    @Operation(summary = "Obtenir le relevé de compte")
    @ApiResponse(responseCode = "200", description = "Relevé récupéré avec succès")
    public ResponseEntity<List<StatementEntryDTO>> getStatement(@PathVariable String accountId) {
        return ResponseEntity.ok(accountService.getStatement(accountId));
    }

}