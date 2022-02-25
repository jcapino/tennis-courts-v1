package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class CancelReservationService extends BaseReservationService {

    public Reservation run(Reservation cancelReservationRequest) {

        Reservation reservationValidated = validateCancelationOrReschedule(cancelReservationRequest);

        BigDecimal refundValue = getRefundValue(reservationValidated);

        return updateReservation(reservationValidated, refundValue, ReservationStatus.CANCELLED);
    }
}
