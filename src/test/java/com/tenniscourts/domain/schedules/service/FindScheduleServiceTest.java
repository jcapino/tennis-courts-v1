package com.tenniscourts.domain.schedules.service;

import com.tenniscourts.MockApplication;
import com.tenniscourts.domain.schedules.model.Schedule;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DataValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class FindScheduleServiceTest {

    @Autowired
    FindScheduleService findScheduleService;

    @Test
    @DisplayName("It must obtain a list with one available schedule filtered by start date time and end date time")
    void itMustObtainAListWithOneAvailableScheduleFilteredByStartDateTimeAndEndDateTime(){
        String startDateTime = "01/01/2020";
        String endDateTime = "31/12/2022";

        List<Schedule> scheduleListResult = findScheduleService.byStartDateTimeAndEndDateTime(startDateTime, endDateTime);

        Assertions.assertNotNull(scheduleListResult);
        Assertions.assertEquals(1, scheduleListResult.size());
    }

    @Test
    @DisplayName("It should get the schedule filtered by id")
    void itShouldGetTheScheduleFilteredById() {
        Long scheduleId = 1L;

        Schedule schedule = findScheduleService.findById(scheduleId);

        Assertions.assertNotNull(schedule);
        Assertions.assertEquals(scheduleId, schedule.getId());
    }

    @Test
    @DisplayName("It must fail when querying the schedule filtered by null Id")
    void itMustFailWhenQueryingTheScheduleFilteredByNullId() {
        Long scheduleId = null;

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> findScheduleService.findById(scheduleId));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field is required", "Schedule Id"), result.getMessage());
    }

    @Test
    @DisplayName("It must fail when querying the schedule filtered by an Id that does not exist")
    void itMustFailWhenQueryingTheScheduleFilteredByAnIdThatDoestNotExist() {
        Long scheduleId = -1L;

        Exception result = Assertions.assertThrows(BusinessException.class, () -> findScheduleService.findById(scheduleId));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("Error: Schedule with ID %s does not exist", scheduleId), result.getMessage());
    }
}
