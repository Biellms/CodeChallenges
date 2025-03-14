package com.itautest.transaction_api.business.service;

import com.itautest.transaction_api.business.services.TransactionService;
import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import com.itautest.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    TransactionRequestDTO transaction;
    StatisticResponseDTO statistic;

    @BeforeEach
    void setUp() {
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        statistic = new StatisticResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void mustPostTransactionWithSuccess() {
        transactionService.postTransactions(transaction);

        List<TransactionRequestDTO> transactionReturn = transactionService.getTransactions(5000);

        assertTrue(transactionReturn.contains(transaction));
    }

    @Test
    void expectionTransactionNegativeValue() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transactionService.postTransactions(new TransactionRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals(ConstantesDTO.EXC_NEGATIVE_VALUE, exception.getMessage());
    }

    @Test
    void expectionTransactionDateTimeGreater() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transactionService.postTransactions(new TransactionRequestDTO(10.0, OffsetDateTime.now().plusMinutes(1))));

        assertEquals(ConstantesDTO.EXC_DATE_TIME_GREATER, exception.getMessage());
    }

    @Test
    void mustDeleteTransactionWithSuccess() {
        transactionService.postTransactions(transaction);
        transactionService.deleteTransactions();

        List<TransactionRequestDTO> transactionReturn = transactionService.getTransactions(5000);

        assertTrue(transactionReturn.isEmpty());
    }

    @Test
    void mustGetTransactionInAndOutOfRange() {
        TransactionRequestDTO transactionOutRange = new TransactionRequestDTO(10.00, OffsetDateTime.now().minusMinutes(1));

        transactionService.postTransactions(transaction);
        transactionService.postTransactions(transactionOutRange);

        List<TransactionRequestDTO> transactionReturn = transactionService.getTransactions(60);

        assertTrue(transactionReturn.contains(transaction));
        assertFalse(transactionReturn.contains(transactionOutRange));
    }

}
