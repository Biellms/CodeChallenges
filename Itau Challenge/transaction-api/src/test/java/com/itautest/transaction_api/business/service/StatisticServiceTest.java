package com.itautest.transaction_api.business.service;


import com.itautest.transaction_api.business.services.StatisticService;
import com.itautest.transaction_api.business.services.TransactionService;
import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {

    @InjectMocks
    StatisticService statisticService;

    @Mock
    TransactionService transactionService;

    TransactionRequestDTO transaction;
    StatisticResponseDTO statistic;

    @BeforeEach
    void setUp() {
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.now());
        statistic = new StatisticResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void mutsGetStatisticWithSuccess(){
        when(transactionService.getTransactions(60))
                .thenReturn(Collections.singletonList(transaction));

        StatisticResponseDTO result = statisticService.getStatisticsTransactions(60);

        verify(transactionService, times(1)).getTransactions(60);
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(statistic);
    }

    @Test
    void mustGetStatisticEmptyList() {
        StatisticResponseDTO statisticEmptyList =
                new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);

        when(transactionService.getTransactions(60))
                .thenReturn(Collections.emptyList());

        StatisticResponseDTO result = statisticService.getStatisticsTransactions(60);

        verify(transactionService, times(1)).getTransactions(60);
        Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(statisticEmptyList);
    }

}
