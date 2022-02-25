package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CompleteReservationService extends BaseReservationService {

    public Reservation run(Reservation reservation) {

        Reservation validatedReservation = validateBeforeCloseReservation(reservation);
        BigDecimal refundValue = reservation.getReservationStatus()
                .equals(ReservationStatus.COMPLETED) ? validatedReservation.getValue() : BigDecimal.ZERO;
        return updateReservation(validatedReservation, refundValue, reservation.getReservationStatus());
    }

    private Reservation validateBeforeCloseReservation(Reservation reservation){

        ArgumentValidator.isRequiredField(reservation.getId(), "Reservation Id");
        ArgumentValidator.isEmptyValue(reservation.getId(), "Reservation Id");

        return reservationRepository
                .findById(reservation.getId())
                .orElseThrow(()-> new BusinessException(
                        String.format("Error: Cannot find reservation with id %s", reservation.getId())
                ));
    }
}
