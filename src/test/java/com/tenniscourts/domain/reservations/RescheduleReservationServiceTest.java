package com.tenniscourts.domain.reservations;

import com.tenniscourts.MockApplication;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.service.RescheduleReservationService;
import com.tenniscourts.domain.schedules.model.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest(classes = {MockApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class RescheduleReservationServiceTest {

    @Autowired
    RescheduleReservationService rescheduleReservationService;

    @Test
    void getRefundValueFullRefund() {
        Schedule schedule = new Schedule();

        LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);
        schedule.setStartDateTime(startDateTime);

        Assertions.assertEquals(
                rescheduleReservationService.getRefundValue(Reservation.builder()
                        .schedule(schedule)
                        .value(new BigDecimal(10L)).build()), new BigDecimal(10));
    }
}