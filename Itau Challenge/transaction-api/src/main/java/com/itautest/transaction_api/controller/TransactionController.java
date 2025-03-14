package com.itautest.transaction_api.controller;

import com.itautest.transaction_api.business.services.TransactionService;
import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    @Operation(description = "Endpoint responsible for listing transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions returned successfully"),
            @ApiResponse(responseCode = "422", description = "Data inputs do not meet the transaction requirements"),
            @ApiResponse(responseCode = "400", description = "Request Error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<TransactionRequestDTO> getTransactions(
            @RequestParam(value = ConstantesDTO.STATISTIC_SEARCH_RANGE, required = false, defaultValue = "60") Integer searchRange) {
        return transactionService.getTransactions(searchRange);
    }

    @PostMapping
    @Operation(description = "Endpoint responsible for adding transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction recorded successfully"),
            @ApiResponse(responseCode = "422", description = "Data inputs do not meet the transaction requirements"),
            @ApiResponse(responseCode = "400", description = "Request Error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> postTransactions(@RequestBody TransactionRequestDTO dto) {
        transactionService.postTransactions(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsible for deleting transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transactions deleted successfully"),
            @ApiResponse(responseCode = "422", description = "Data inputs do not meet the transaction requirements"),
            @ApiResponse(responseCode = "400", description = "Request Error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTransactions() {
        transactionService.deleteTransactions();
        return ResponseEntity.ok().build();
    }

}
