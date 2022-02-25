package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RescheduleReservationService extends BaseReservationService {

    private final BookReservationService bookReservationService;

    public Reservation run(Reservation rescheduleReservationRequest) {

        Reservation previousReservation = validateRescheduleRequestBeforeSave(rescheduleReservationRequest);

        Reservation newReservation = prepareReservation(previousReservation, rescheduleReservationRequest);

        updateReservation(previousReservation, getRefundValue(previousReservation), ReservationStatus.RESCHEDULED);
        bookReservationService.run(newReservation);



        newReservation.setPreviousReservation(previousReservation);

        return newReservation;
    }

    private Reservation validateRescheduleRequestBeforeSave(Reservation rescheduleReservationRequest){

        Reservation previousReservation = validateCancelationOrReschedule(rescheduleReservationRequest);

        if (previousReservation.getSchedule().getId().equals(rescheduleReservationRequest.getSchedule().getId())) {
            throw new BusinessException("Cannot reschedule to the same slot.");
        }

        return previousReservation;
    }

    public Reservation prepareReservation(Reservation previousReservation, Reservation rescheduleReservationRequest) {

        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(previousReservation, reservation);

        reservation.setId(null);
        reservation.setValue(previousReservation.getValue());
        reservation.setSchedule(rescheduleReservationRequest.getSchedule());

        return reservation;
    }
}
