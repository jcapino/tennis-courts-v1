package com.tenniscourts.domain.schedules.service;

import com.tenniscourts.MockApplication;
import com.tenniscourts.domain.commons.DateUtils;
import com.tenniscourts.domain.schedules.model.Schedule;
import com.tenniscourts.domain.schedules.service.CreateScheduleService;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DataValidationException;
import com.tenniscourts.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CreateScheduleServiceTest {

    @Autowired
    CreateScheduleService createScheduleService;

    @Test
    @DisplayName("It should successfully create a new schedule for a given tennis court")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldSuccessfullyCreateANewScheduleForAGivenTennisCourt() {
        Long tennisCourtId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(5);

        Schedule result = createScheduleService.run(tennisCourtId, startDateTime);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getId());
    }

    @Test
    @DisplayName("It should fail when trying to create a schedule given a tennis court with null id")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldThrowExceptionWhenTryingToCreateAScheduleGivenATennisCourtWithNullId() {
        Long tennisCourtId = null;
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(5);

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> createScheduleService.run(tennisCourtId, startDateTime));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field is required", "Tennis Court Id"), result.getMessage());
    }

    @Test
    @DisplayName("It should fail when trying to create a schedule given a tennis court with null start date time")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldThrowExceptionWhenTryingToCreateAScheduleGivenATennisCourtWithNullStartDateTime() {
        Long tennisCourtId = 1L;
        LocalDateTime startDateTime = null;

        Exception result = Assertions.assertThrows(DataValidationException.class, () -> createScheduleService.run(tennisCourtId, startDateTime));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("The value of the %s field is required", "Start Date Time"), result.getMessage());
    }

    @Test
    @DisplayName("It should fail when trying to create a schedule given a tennis court with scheduled reservation")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldThrowExceptionWhenTryingToCreateAScheduleGivenATennisCourtWithScheduledReservation() {
        Long tennisCourtId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(6);

        createScheduleService.run(tennisCourtId, startDateTime);

        Exception result = Assertions.assertThrows(BusinessException.class, () -> createScheduleService.run(tennisCourtId, startDateTime));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Error: The schedule you are trying to create already exists or intersects with another schedule", result.getMessage());
    }

    @Test
    @DisplayName("It should fail when trying to create a schedule given a tennis court that doesn't exist")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void itShouldThrowExceptionWhenTryingToCreateAScheduleGivenATennisCourtThatDoesNotExist() {
        Long tennisCourtId = -1L;
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(7);

        Exception result = Assertions.assertThrows(EntityNotFoundException.class, () -> createScheduleService.run(tennisCourtId, startDateTime));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(String.format("Error: Entity with id %s not found", tennisCourtId), result.getMessage());
    }
}
