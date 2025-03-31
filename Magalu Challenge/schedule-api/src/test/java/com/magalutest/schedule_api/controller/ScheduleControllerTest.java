package com.magalutest.schedule_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.magalutest.schedule_api.business.service.ScheduleService;
import com.magalutest.schedule_api.controller.dto.ScheduleController;
import com.magalutest.schedule_api.controller.dto.in.ScheduleRecordIn;
import com.magalutest.schedule_api.controller.dto.out.ScheduleRecordOut;
import com.magalutest.schedule_api.infrastructure.enums.StatusNotificationEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ScheduleControllerTest {

    @InjectMocks
    ScheduleController scheduleController;

    @Mock
    ScheduleService scheduleService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private ScheduleRecordIn scheduleIn;
    private ScheduleRecordOut scheduleOut;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
        objectMapper.registerModule(new JavaTimeModule());

        scheduleIn = new ScheduleRecordIn("email@email.com", "55887996578",
                "Please contact the store urgently", LocalDateTime.of(2025, 1, 2, 11, 1, 1));

        scheduleOut = new ScheduleRecordOut(1L, "email@email.com", "55887996578",
                "Please contact the store urgently", LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificationEnum.SCHEDULED);
    }

    @Test
    void mustCreateScheduleWithSuccess() throws Exception {
        when(scheduleService.postSchedule(scheduleIn)).thenReturn(scheduleOut);

        mockMvc.perform(post("/schedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(scheduleIn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.emailDestinatary").value(scheduleOut.emailDestinatary()))
                .andExpect(jsonPath("$.phoneDestinatary").value(scheduleOut.phoneDestinatary()))
                .andExpect(jsonPath("$.message").value(scheduleOut.message()))
                .andExpect(jsonPath("$.dateHourSend").value("02-01-2025 11:01:01"))
                .andExpect(jsonPath("$.statusNotification").value(StatusNotificationEnum.SCHEDULED.toString()));

        verify(scheduleService, times(1)).postSchedule(scheduleIn);
    }

    @Test
    void mustGetScheduleByIdWithSuccess() throws Exception {
        when(scheduleService.getScheduleById(1L)).thenReturn(scheduleOut);

        mockMvc.perform(get("/schedule/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.emailDestinatary").value(scheduleOut.emailDestinatary()))
                .andExpect(jsonPath("$.phoneDestinatary").value(scheduleOut.phoneDestinatary()))
                .andExpect(jsonPath("$.message").value(scheduleOut.message()));
    }

    @Test
    void mustCancelScheduleWithSuccess() throws Exception {
        doNothing().when(scheduleService).cancelSchedule(1L);

        mockMvc.perform(delete("/schedule/{id}", 1L))
                .andExpect(status().isAccepted());
    }

}
