package com.itautest.transaction_api.controller;

import com.itautest.transaction_api.business.services.StatisticService;
import com.itautest.transaction_api.controller.dtos.ConstantesDTO;
import com.itautest.transaction_api.controller.dtos.StatisticResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class StatisticControllerTest {

    @InjectMocks
    StatisticsController statisticsController;

    @Mock
    StatisticService statisticService;

    StatisticResponseDTO statistic;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
        statistic = new StatisticResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void mustGetStatisticWithSuccess() throws Exception {
        when(statisticService.getStatisticsTransactions(60)).thenReturn(statistic);

        mockMvc.perform(get(ConstantesDTO.URL_STATISTIC).
                        param(ConstantesDTO.STATISTIC_SEARCH_RANGE, "60")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(2L));
    }

}
