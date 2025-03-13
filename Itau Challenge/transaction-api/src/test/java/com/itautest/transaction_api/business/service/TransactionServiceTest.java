package com.itautest.transaction_api.business.service;

import com.itautest.transaction_api.business.services.StatisticService;
import com.itautest.transaction_api.business.services.TransactionService;
import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import com.itautest.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    void putTransactionWithSuccess() {
        transactionService.putTransactions(transaction);

        List<TransactionRequestDTO> transactionReturn = transactionService.getTransactions(5000);

        assertTrue(transactionReturn.contains(transaction));
    }

    @Test
    void expectionTransactionNegativeValue() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transactionService.putTransactions(new TransactionRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals(ConstantesDTO.EXC_NEGATIVE_VALUE, exception.getMessage());
    }

    @Test
    void expectionTransactionDateTimeGreater() {
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transactionService.putTransactions(new TransactionRequestDTO(10.0, OffsetDateTime.now().plusMinutes(1))));

        assertEquals(ConstantesDTO.EXC_DATE_TIME_GREATER, exception.getMessage());
    }

    @Test
    void deleteTransactionWithSuccess() {
        transactionService.putTransactions(transaction);
        transactionService.deleteTransactions();

        List<TransactionRequestDTO> transactionReturn = transactionService.getTransactions(5000);

        assertTrue(transactionReturn.isEmpty());
    }

    @Test
    void getTransactionInAndOutOfRange(){

        TransactionRequestDTO transactionOutRange = new TransactionRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transactionService.putTransactions(transaction);
        transactionService.putTransactions(transactionOutRange);

        List<TransactionRequestDTO> transactionReturn = transactionService.getTransactions(60);

        assertTrue(transactionReturn.contains(transaction));
        assertFalse(transactionReturn.contains(transactionOutRange));
    }

}
