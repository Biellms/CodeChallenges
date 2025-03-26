package com.magalutest.schedule_api.infrastructure.entities;

import com.magalutest.schedule_api.infrastructure.enums.StatusNotificationEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "schedule")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailDestinatary;
    private String phoneDestinatary;
    private LocalDateTime dateHourSend;
    private LocalDateTime dateHourSchedule;
    private LocalDateTime dateHourModification;
    private String message;
    private StatusNotificationEnum statusNotification;

    @PrePersist
    private void prePersist() {
        dateHourSchedule = LocalDateTime.now();
        statusNotification = StatusNotificationEnum.SCHEDULED;
    }
}