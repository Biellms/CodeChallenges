package com.magalutest.schedule_api.controller.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ScheduleRecordOut (
        String emailDestinatary,
        String phoneDestinatary,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime dateHourSend) {

}
