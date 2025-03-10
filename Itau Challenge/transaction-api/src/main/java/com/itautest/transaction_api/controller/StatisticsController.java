package com.itautest.transaction_api.controller;

import com.itautest.transaction_api.business.services.StatisticService;
import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticService statisticService;

    public ResponseEntity<StatisticResponseDTO> getStatistics(
            @RequestParam(value = "searchRange", required = false, defaultValue = "60") Integer searchRange) {

        return ResponseEntity.ok(statisticService.getStatisticsTransactions(searchRange));
    }

}