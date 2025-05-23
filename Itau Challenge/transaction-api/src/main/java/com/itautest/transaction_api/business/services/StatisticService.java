package com.itautest.transaction_api.business.services;

import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    private final TransactionService transactionService;

    public StatisticResponseDTO getStatisticsTransactions (Integer searchRange) {
        log.info("[StatisticService] getStatisticsTransactions: Search Range -> " + searchRange + "s");
        long start = System.currentTimeMillis();

        List<TransactionRequestDTO> transactions = transactionService.getTransactions(searchRange);

        if (transactions.isEmpty()) {
            return new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics statisticsTransactions = transactions.stream()
                .mapToDouble(TransactionRequestDTO::value).summaryStatistics();

        long requestTime = System.currentTimeMillis() - start;
        log.info("[StatisticService] getStatisticsTransactions: Statistics successfully returned" +
                " | Request Time -> " + requestTime + "ms");

        return new StatisticResponseDTO(
                statisticsTransactions.getCount(),
                statisticsTransactions.getSum(),
                statisticsTransactions.getAverage(),
                statisticsTransactions.getMin(),
                statisticsTransactions.getMax()
        );
    }


}
