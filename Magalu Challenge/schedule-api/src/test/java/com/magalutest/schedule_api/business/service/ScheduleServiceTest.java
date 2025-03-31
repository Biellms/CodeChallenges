package com.magalutest.schedule_api.business.service;

import com.magalutest.schedule_api.business.mapper.ScheduleMapper;
import com.magalutest.schedule_api.controller.dto.in.ScheduleRecordIn;
import com.magalutest.schedule_api.controller.dto.out.ScheduleRecordOut;
import com.magalutest.schedule_api.infrastructure.entities.Schedule;
import com.magalutest.schedule_api.infrastructure.enums.StatusNotificationEnum;
import com.magalutest.schedule_api.infrastructure.exception.NotFoundException;
import com.magalutest.schedule_api.infrastructure.repositories.ScheduleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @InjectMocks
    ScheduleService scheduleService;

    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    ScheduleMapper scheduleMapper;

    private ScheduleRecordIn scheduleIn;
    private ScheduleRecordOut scheduleOut;
    private Schedule scheduleEntity;

    @BeforeEach
    void setUp() {
        scheduleEntity = new Schedule(1L,
                "email@email.com",
                "55887996578",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                LocalDateTime.now(),
                null,
                "Please contact the store urgently",
                StatusNotificationEnum.SCHEDULED);

        scheduleIn = new ScheduleRecordIn("email@email.com",
                "55887996578",
                "Please contact the store urgently",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1));

        scheduleOut = new ScheduleRecordOut(1L,
                "email@email.com",
                "55887996578",
                "Please contact the store urgently",
                LocalDateTime.of(2025, 1, 2, 11, 1, 1),
                StatusNotificationEnum.SCHEDULED);
    }

    @Test
    void mustCreateScheduleWithSuccess() {
        when(scheduleMapper.toEntity(scheduleIn)).thenReturn(scheduleEntity);
        when(scheduleRepository.save(scheduleEntity)).thenReturn(scheduleEntity);
        when(scheduleMapper.toOut(scheduleEntity)).thenReturn(scheduleOut);

        ScheduleRecordOut scheduleOutTest = scheduleService.postSchedule(scheduleIn);

        verify(scheduleMapper, times(1)).toEntity(scheduleIn);
        verify(scheduleRepository, times(1)).save(scheduleEntity);
        verify(scheduleMapper, times(1)).toOut(scheduleEntity);

        assertThat(scheduleOutTest).usingRecursiveComparison().isEqualTo(scheduleOut);
    }

    @Test
    void mustGetScheduleByIdWithSuccess() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(scheduleEntity));
        when(scheduleMapper.toOut(scheduleEntity)).thenReturn(scheduleOut);

        ScheduleRecordOut scheduleOutTest = scheduleService.getScheduleById(1L);

        verify(scheduleRepository, times(1)).findById(1L);
        verify(scheduleMapper, times(1)).toOut(scheduleEntity);

        assertThat(scheduleOutTest).usingRecursiveComparison().isEqualTo(scheduleOut);
    }

    @Test
    void mustCancelScheduleByIdWithSuccess() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(scheduleEntity));
        when(scheduleMapper.toOut(scheduleEntity)).thenReturn(scheduleOut);

        ScheduleRecordOut scheduleOutTest = scheduleService.getScheduleById(1L);

        verify(scheduleRepository, times(1)).findById(1L);
        verify(scheduleMapper, times(1)).toOut(scheduleEntity);

        assertThat(scheduleOutTest).usingRecursiveComparison().isEqualTo(scheduleOut);
    }

    @Test
    void mustCancelScheduleWithSuccess() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(scheduleEntity));
        when(scheduleMapper.toEntityCanceled(scheduleEntity)).thenReturn(scheduleEntity);

        scheduleService.cancelSchedule(1L);

        verify(scheduleRepository, times(1)).findById(1L);
        verify(scheduleMapper, times(1)).toEntityCanceled(scheduleEntity);
        verify(scheduleRepository, times(1)).save(scheduleEntity);
    }

    @Test
    void mustThrowNotFoundExceptionWhenCancelingSchedule() {
        when(scheduleRepository.findById(99L)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            scheduleService.cancelSchedule(99L);
        });

        assertEquals("Id Not Found", exception.getMessage());

        verify(scheduleRepository, never()).save(any());
        verify(scheduleMapper, never()).toEntityCanceled(any());
    }

    @Test
    void mustCreateScheduleWithBuilder() {
        Schedule schedule = Schedule.builder()
                .id(1L)
                .emailDestinatary("email@email.com")
                .phoneDestinatary("55887996578")
                .dateHourSend(LocalDateTime.of(2025, 1, 2, 11, 1, 1))
                .dateHourSchedule(null)
                .dateHourModification(null)
                .message("Please contact the store urgently")
                .statusNotification(StatusNotificationEnum.SCHEDULED)
                .build();

        assertNotNull(schedule);
        assertEquals(1L, schedule.getId());
        assertEquals(scheduleOut.emailDestinatary(), schedule.getEmailDestinatary());
        assertEquals(scheduleOut.phoneDestinatary(), schedule.getPhoneDestinatary());
        assertEquals(scheduleOut.dateHourSend(), schedule.getDateHourSend());
        assertNull(schedule.getDateHourSchedule());
        assertNull(schedule.getDateHourModification());
        assertEquals(scheduleOut.message(), schedule.getMessage());
        assertEquals(scheduleOut.statusNotification(), schedule.getStatusNotification());
    }

    @Test
    void mustSetDefaultValuesOnPrePersistUsingReflection() throws Exception {
        Schedule schedule = new Schedule();

        Method prePersistMethod = Schedule.class.getDeclaredMethod("prePersist");
        prePersistMethod.setAccessible(true);

        prePersistMethod.invoke(schedule);

        assertNotNull(schedule.getDateHourSchedule());
        assertEquals(StatusNotificationEnum.SCHEDULED, schedule.getStatusNotification());
    }


}
