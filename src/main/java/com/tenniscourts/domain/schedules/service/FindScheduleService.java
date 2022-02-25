package com.tenniscourts.domain.schedules.service;

import com.tenniscourts.domain.commons.DataValidator;
import com.tenniscourts.domain.commons.DateUtils;
import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.domain.commons.validator.DataStatusValidator;
import com.tenniscourts.domain.schedules.model.Schedule;
import com.tenniscourts.domain.schedules.repository.ScheduleRepository;
import com.tenniscourts.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule findById(Long scheduleId) {

        ArgumentValidator.isRequiredField(scheduleId, "Schedule Id");

        return scheduleRepository
                .findById(scheduleId)
                .orElseThrow(()->new BusinessException(String.format("Error: Schedule with ID %s does not exist", scheduleId)));
    }

    public List<Schedule> byStartDateTimeAndEndDateTime(String startDate, String endDate) {

        if (DataStatusValidator.isEmptyValue(startDate) && DataStatusValidator.isEmptyValue(endDate)) {
            return scheduleRepository.findAllAvailableSchedules().orElse(Collections.emptyList());
        }

        validateDataBeforeQueryByDates(startDate, endDate);

        LocalDate validatedStartDate = DateUtils.stringToSimpleDateFormatLocalDate(startDate);
        LocalDate validatedEndDate = DateUtils.stringToSimpleDateFormatLocalDate(endDate);

        return scheduleRepository.findByScheduleStartDateTimeAndEndDateTime_OrderByStartDateTime(
                LocalDateTime.of(validatedStartDate, LocalTime.of(0, 0)),
                LocalDateTime.of(validatedEndDate, LocalTime.of(23, 59))
        ).orElseThrow(() -> new BusinessException("No data found"));
    }

    private void validateDataBeforeQueryByDates(String startDate, String endDate){

        ArgumentValidator.isRequiredField(startDate, "Start Date Time");
        ArgumentValidator.isEmptyValue(startDate, "Start Date Time");
        ArgumentValidator.isRequiredField(endDate, "End Date Time");
        ArgumentValidator.isEmptyValue(endDate, "End Date Time");

        DataValidator.isSimpleDateFormat(startDate, "Start Date Time");
        DataValidator.isSimpleDateFormat(endDate, "End Date Time");
    }

    public List<Schedule> byTennisCourtId(Long tennisCourtId) {

        ArgumentValidator.isRequiredField(tennisCourtId, "Tennis Court Id");

        return scheduleRepository
                .findByTennisCourt_Id(tennisCourtId)
                .orElse(Collections.emptyList());
    }
}
