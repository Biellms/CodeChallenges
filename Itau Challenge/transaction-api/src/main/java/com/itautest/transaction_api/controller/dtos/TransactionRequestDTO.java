package com.itautest.transaction_api.controller.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double value, OffsetDateTime date) {

}
