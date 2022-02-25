package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UpdateReservationService {

    private CancelReservationService cancelReservationService;
    private RescheduleReservationService rescheduleReservationService;
    private CompleteReservationService completeReservationService;

    public Reservation run(Reservation reservation) {

        if (reservation.getReservationStatus().equals(ReservationStatus.CANCELLED)) {
            return cancelReservationService.run(reservation);
        }

        if (reservation.getReservationStatus().equals(ReservationStatus.RESCHEDULED)) {
            return rescheduleReservationService.run(reservation);
        }

        if (reservation.getReservationStatus().equals(ReservationStatus.COMPLETED)
                || reservation.getReservationStatus().equals(ReservationStatus.NOT_ASSIST)) {
            return completeReservationService.run(reservation);
        }

        throw new BusinessException("Error: The requested action is not parameterized");
    }
}
