package com.itautest.transaction_api.business.services;

import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import com.itautest.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionRequestDTO> transactionList = new ArrayList<>();

    public void postTransactions(TransactionRequestDTO dto) {
        log.info("[TransactionService] postTransactions: " + dto);
        long start = System.currentTimeMillis();

        if (dto.date().isAfter(OffsetDateTime.now())) {
            log.error("[TransactionService] postTransactions: " + ConstantesDTO.EXC_DATE_TIME_GREATER);
            throw new UnprocessableEntity(ConstantesDTO.EXC_DATE_TIME_GREATER);
        }
        if(dto.value() < 0){
            log.error("[TransactionService] postTransactions: " + ConstantesDTO.EXC_NEGATIVE_VALUE);
            throw new UnprocessableEntity(ConstantesDTO.EXC_NEGATIVE_VALUE);
        }

        transactionList.add(dto);

        long requestTime = System.currentTimeMillis() - start;
        log.info("[TransactionService] postTransactions: Transaction successfully added" +
                " | Request Time -> " + requestTime + "ms");
    }

    public void deleteTransactions() {
        log.info("[TransactionService] deleteTransactions: Started ");
        long start = System.currentTimeMillis();

        transactionList.clear();

        long requestTime = System.currentTimeMillis() - start;
        log.info("[TransactionService] deleteTransactions: Transactions successfully deleted" +
                " | Request Time -> " + requestTime + "ms");
    }

    public List<TransactionRequestDTO> getTransactions(Integer searchRange) {
        log.info("[TransactionService] getTransactions: Search Range -> " + searchRange + "s");
        long start = System.currentTimeMillis();

        OffsetDateTime dateHourRange = OffsetDateTime.now().minusSeconds(searchRange);

        long requestTime = System.currentTimeMillis() - start;
        log.info("[TransactionService] getTransactions: Transactions successfully returned" +
                " | Request Time -> " + requestTime + "ms");

        return transactionList.stream()
                .filter(transaction -> transaction.date()
                        .isAfter(dateHourRange)).toList();
    }

}
