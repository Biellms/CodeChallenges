package com.magalutest.schedule_api.controller.dto;

import com.magalutest.schedule_api.business.service.ScheduleService;
import com.magalutest.schedule_api.controller.dto.in.ScheduleRecordIn;
import com.magalutest.schedule_api.controller.dto.out.ScheduleRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleRecordOut> postSchedule(@RequestBody ScheduleRecordIn schedule) {
        return ResponseEntity.ok(scheduleService.postSchedule(schedule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleRecordOut> getScheduleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelSchedule(@PathVariable("id") Long id) {
        scheduleService.cancelSchedule(id);
        return ResponseEntity.accepted().build();
    }

}
