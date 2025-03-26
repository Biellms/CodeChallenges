package com.magalutest.schedule_api.infrastructure.repositories;

import com.magalutest.schedule_api.infrastructure.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
