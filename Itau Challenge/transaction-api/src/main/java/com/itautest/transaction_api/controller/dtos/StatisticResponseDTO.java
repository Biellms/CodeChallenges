package com.itautest.transaction_api.controller.dtos;

public record StatisticResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max) {
}
