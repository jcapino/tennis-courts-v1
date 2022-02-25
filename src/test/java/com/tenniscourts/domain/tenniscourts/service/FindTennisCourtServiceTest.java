package com.tenniscourts.domain.tenniscourts.service;

import com.tenniscourts.MockApplication;
import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import com.tenniscourts.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class FindTennisCourtServiceTest {

    @Autowired
    FindTennisCourtService findTennisCourtService;

    @Test
    @DisplayName("It must obtain a tennis court filtered by Id")
    void itMustObtainATennisCourtFilteredById() {
        Long tennisCourtId = 1L;
        TennisCourt tennisCourtResult = findTennisCourtService.findTennisCourtById(tennisCourtId);

        Assertions.assertNotNull(tennisCourtResult);
        Assertions.assertEquals(1L, tennisCourtResult.getId());
    }

    @Test
    @DisplayName("It should throw a not found exception")
    void itShouldThrowNotFoundException() {
        Long tennisCourtId = -1L;

        Exception result = Assertions.assertThrows(EntityNotFoundException.class, () -> findTennisCourtService.findTennisCourtById(tennisCourtId));

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Tennis Court not found.", result.getMessage());
    }

    @Test
    @DisplayName("It should obtain a tennis court with a once schedule available")
    void itShouldObtainATennisCourtWithAOnceScheduleAvailable() {
        Long tennisCourtId = 1L;
        TennisCourt tennisCourtResult = findTennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId);

        Assertions.assertNotNull(tennisCourtResult);
        Assertions.assertEquals(1L, tennisCourtResult.getId());
        Assertions.assertNotNull(tennisCourtResult.getTennisCourtSchedules());
        Assertions.assertEquals(1, tennisCourtResult.getTennisCourtSchedules().size());
    }
}
