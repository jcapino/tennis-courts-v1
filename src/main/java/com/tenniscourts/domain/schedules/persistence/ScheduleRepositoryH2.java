package com.tenniscourts.domain.schedules.persistence;

import com.tenniscourts.domain.schedules.model.Schedule;
import com.tenniscourts.domain.schedules.persistence.dao.ScheduleDao;
import com.tenniscourts.domain.schedules.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ScheduleRepositoryH2 implements ScheduleRepository {

    private final ScheduleDao scheduleDao;

    @Override
    public Optional<List<Schedule>> findAllAvailableSchedules() {
        return Optional.ofNullable(scheduleDao.findAllAvailableSchedules());
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleDao.findById(id);
    }

    @Override
    public Optional<Schedule> save(Schedule schedule) {
        return Optional.of(scheduleDao.save(schedule));
    }

    @Override
    public Optional<Schedule> findByTennisCourtIdAndStartDateTimeIsNotCrossedWithAnotherTime(Long tennisCourtId, LocalDateTime startDateTime) {
        return Optional.ofNullable(scheduleDao.findByTennisCourtIdAndStartDateTimeIsNotCrossedWithAnotherTime(tennisCourtId, startDateTime));
    }

    @Override
    public Optional<List<Schedule>> findByTennisCourt_Id(Long id) {
        return Optional.of(scheduleDao.findByTennisCourt_Id(id));
    }

    @Override
    public Optional<List<Schedule>> findByScheduleStartDateTimeAndEndDateTime_OrderByStartDateTime(LocalDateTime startDate, LocalDateTime endDate) {
        return Optional.ofNullable(
                scheduleDao.findByTennisCourtStartDateTimeAndEndDateTime_OrderByStartDateTime(startDate, endDate)
        );
    }
}
