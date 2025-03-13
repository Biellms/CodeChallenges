package com.itautest.transaction_api.controller;

import com.itautest.transaction_api.business.services.StatisticService;
import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantesDTO.URL_STATISTIC)
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticService statisticService;

    @GetMapping
    @Operation(description = "Endpoint responsible for searching transactions statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction statistics searched successfully"),
            @ApiResponse(responseCode = "422", description = "Data inputs do not meet the transaction requirements"),
            @ApiResponse(responseCode = "400", description = "Request Error"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<StatisticResponseDTO> getStatistics(
            @RequestParam(value = ConstantesDTO.STATISTIC_SEARCH_RANGE, required = false, defaultValue = "60") Integer searchRange) {

        return ResponseEntity.ok(statisticService.getStatisticsTransactions(searchRange));
    }

}