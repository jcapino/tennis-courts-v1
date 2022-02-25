package com.tenniscourts.domain.schedules.service;

import com.tenniscourts.domain.commons.DatabaseMessages;
import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DatabaseException;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.domain.schedules.model.Schedule;
import com.tenniscourts.domain.schedules.repository.ScheduleRepository;
import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import com.tenniscourts.domain.tenniscourts.model.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TennisCourtRepository tennisCourtRepository;

    public Schedule run(Long tennisCourtId, LocalDateTime startDateTime) {

        validateDataBeforeSave(tennisCourtId, startDateTime);

        TennisCourt tennisCourt = tennisCourtRepository
                .findById(tennisCourtId)
                .orElseThrow(() -> new EntityNotFoundException(DatabaseMessages.ENTITY_NOT_FOUND.getMessage(), tennisCourtId));
        LocalDateTime endDateTime = startDateTime.plusHours(1);

        Schedule newSchedule = new Schedule(tennisCourt, startDateTime, endDateTime, null);
        return save(newSchedule);
    }

    private void validateDataBeforeSave(Long tennisCourtId, LocalDateTime startDateTime) {
        ArgumentValidator.isRequiredField(tennisCourtId, "Tennis Court Id");
        ArgumentValidator.isRequiredField(startDateTime, "Start Date Time");

        Optional<Schedule> scheduleExists = scheduleRepository.findByTennisCourtIdAndStartDateTimeIsNotCrossedWithAnotherTime(tennisCourtId, startDateTime);

        if (Boolean.TRUE.equals(scheduleExists.isPresent())) {
            throw new BusinessException("Error: The schedule you are trying to create already exists or intersects with another schedule");
        }
    }

    private Schedule save(Schedule schedule) {
        return scheduleRepository
                .save(schedule)
                .orElseThrow(() -> new BusinessException("Error: Failed to persist on entity [Schedule]"));
    }
}
