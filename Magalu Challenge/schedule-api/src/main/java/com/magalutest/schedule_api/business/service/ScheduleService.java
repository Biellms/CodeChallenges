package com.magalutest.schedule_api.business.service;

import com.magalutest.schedule_api.business.mapper.ScheduleMapper;
import com.magalutest.schedule_api.controller.dto.in.ScheduleRecordIn;
import com.magalutest.schedule_api.controller.dto.out.ScheduleRecordOut;
import com.magalutest.schedule_api.infrastructure.entities.Schedule;
import com.magalutest.schedule_api.infrastructure.exception.NotFoundException;
import com.magalutest.schedule_api.infrastructure.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleRecordOut postSchedule(ScheduleRecordIn scheduleIn) {
        log.info("[ScheduleService] postSchedule: executed -> " + scheduleIn);

        return scheduleMapper.toOut(
                scheduleRepository.save(
                    scheduleMapper.toEntity(scheduleIn)));
    }

    public ScheduleRecordOut getScheduleById(Long id) {
        log.info("[ScheduleService] getScheduleById: executed -> id: " + id);

        return scheduleMapper.toOut(
                scheduleRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Id Not Found")));
    }

    public void cancelSchedule(Long id) {
        log.info("[ScheduleService] cancelSchedule: executed -> id: " + id);

        Schedule scheduleEntity =  scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id Not Found"));

        scheduleRepository.save(scheduleMapper.toEntityCanceled(scheduleEntity));
    }

}
