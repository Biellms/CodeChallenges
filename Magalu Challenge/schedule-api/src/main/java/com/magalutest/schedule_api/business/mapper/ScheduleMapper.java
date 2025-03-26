package com.magalutest.schedule_api.business.mapper;

import com.magalutest.schedule_api.controller.dto.in.ScheduleRecordIn;
import com.magalutest.schedule_api.controller.dto.out.ScheduleRecordOut;
import com.magalutest.schedule_api.infrastructure.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ScheduleMapper {

    Schedule toEntity(ScheduleRecordIn schedule);

    ScheduleRecordOut toOut(Schedule schedule);

    @Mapping(target = "dateHourModification", expression = "java(LocalDateTime.now())")
    @Mapping(target = "statusNotification", expression = "java(StatusNotificationEnum.CANCELED)")
    Schedule toEntityCanceled(Schedule schedule);
}
