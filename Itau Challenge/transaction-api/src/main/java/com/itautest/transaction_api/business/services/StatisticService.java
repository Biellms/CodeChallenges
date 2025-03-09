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
        log.info("[TransactionService] getStatisticsTransactions: Search Range -> " + searchRange);

        long start = System.currentTimeMillis();

        List<TransactionRequestDTO> transactions = transactionService.getTransactions(searchRange);

        if (transactions.isEmpty()) {
            return new StatisticResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics statisticsTransactions = transactions.stream()
                .mapToDouble(TransactionRequestDTO::value).summaryStatistics();

        long finish = System.currentTimeMillis();
        long requestTime = finish - start;
        log.info("[TransactionService] getStatisticsTransactions: Request Time -> " + requestTime + "ms");

        log.info("[TransactionService] getStatisticsTransactions: Statistics successfully returned");

        return new StatisticResponseDTO(
                statisticsTransactions.getCount(),
                statisticsTransactions.getSum(),
                statisticsTransactions.getAverage(),
                statisticsTransactions.getMin(),
                statisticsTransactions.getMax()
        );
    }


}
