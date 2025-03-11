package com.itautest.transaction_api.business.services;

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

    public void putTransactions(TransactionRequestDTO dto) {

        log.info("[TransactionService] putTransactions: " + dto);

        if (dto.date().isAfter(OffsetDateTime.now())) {
            log.error("[TransactionService] putTransactions: Data and time greater than current data and time");
            throw new UnprocessableEntity("Data and time greater than current data and time");
        }
        if(dto.value() < 0){
            log.error("[TransactionService] putTransactions: Cannot have a value less than 0");
            throw new UnprocessableEntity("Cannot have a value less than 0");
        }

        transactionList.add(dto);

        log.info("[TransactionService] putTransactions: Transaction successfully added");
    }

    public void deleteTransactions() {
        log.info("[TransactionService] deleteTransactions: Started ");
        transactionList.clear();
        log.info("[TransactionService] deleteTransactions: Transactions successfully deleted ");
    }

    public List<TransactionRequestDTO> getTransactions(Integer searchRange){
        log.info("[TransactionService] getTransactions: Search Range -> " + searchRange + "s");
        OffsetDateTime dateHourRange = OffsetDateTime.now().minusSeconds(searchRange);

        log.info("[TransactionService] getTransactions: Transactions successfully returned");
        return transactionList.stream()
                .filter(transaction -> transaction.date()
                        .isAfter(dateHourRange)).toList();
    }

}
