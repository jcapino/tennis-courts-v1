package com.tenniscourts.domain.schedules.repository;

import com.tenniscourts.domain.schedules.model.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    Optional<List<Schedule>> findAllAvailableSchedules();
    Optional<Schedule> findById(Long id);
    Optional<Schedule> save(Schedule schedule);
    Optional<Schedule> findByTennisCourtIdAndStartDateTimeIsNotCrossedWithAnotherTime(Long tennisCourtId, LocalDateTime startDateTime);
    Optional<List<Schedule>> findByTennisCourt_Id(Long id);
    Optional<List<Schedule>> findByScheduleStartDateTimeAndEndDateTime_OrderByStartDateTime(LocalDateTime startDate, LocalDateTime endDate);
}