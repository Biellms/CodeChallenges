package com.itautest.transaction_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itautest.transaction_api.business.services.TransactionService;
import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.TransactionRequestDTO;
import com.itautest.transaction_api.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    TransactionRequestDTO transaction;

    MockMvc mockMvc;

    @Autowired
    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(){
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
        transaction = new TransactionRequestDTO(20.0, OffsetDateTime.of(2025, 3, 13, 21, 30, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void mustPostTransactionWithSuccess() throws Exception {
        doNothing().when(transactionService).postTransactions(transaction);

        mockMvc.perform(post(ConstantesDTO.URL_TRANSACTION)
                        .content(objectMapper.writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void mustThrowExceptionPostTransaction() throws Exception {
        doThrow(new UnprocessableEntity("Request Error")).when(transactionService).postTransactions(transaction);

        mockMvc.perform(post(ConstantesDTO.URL_TRANSACTION)
                        .content(objectMapper.writeValueAsString(transaction))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void mustDeleteTransactionWithSuccess() throws Exception {
        doNothing().when(transactionService).deleteTransactions();

        mockMvc.perform(delete(ConstantesDTO.URL_TRANSACTION))
                .andExpect(status().isOk());
    }

}
