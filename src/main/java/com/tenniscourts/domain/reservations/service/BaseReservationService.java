package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.BusinessException;
import com.tenniscourts.exceptions.DatabaseException;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import com.tenniscourts.domain.reservations.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public abstract class BaseReservationService {

    @Autowired
    protected ReservationRepository reservationRepository;

    protected Reservation validateCancelationOrReschedule(Reservation reservationRequest) {
        ArgumentValidator.isRequiredField(reservationRequest.getId(), "Reservation Id");
        ArgumentValidator.isEmptyValue(reservationRequest.getId(), "Reservation Id");

        return reservationRepository.findById(reservationRequest.getId()).map(reservation -> {

            if (!ReservationStatus.READY_TO_PLAY.equals(reservation.getReservationStatus())) {
                throw new IllegalArgumentException("Cannot cancel/reschedule because it's not in ready to play status.");
            }

            if (reservation.getSchedule().getStartDateTime().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Can cancel/reschedule only future dates.");
            }

            return reservation;
        }).orElseThrow(() -> new EntityNotFoundException("Reservation not found."));
    }

    public Reservation updateReservation(Reservation reservation, BigDecimal refundValue, ReservationStatus status) {
        reservation.setReservationStatus(status);
        reservation.setValue(reservation.getValue().subtract(refundValue));
        reservation.setRefundValue(refundValue);
        return save(reservation);
    }

    public Reservation save(Reservation reservation) {
        Optional<Reservation> reservationDone;
        try {
            reservationDone = reservationRepository.save(reservation);
        } catch (Exception ex) {
            throw new DatabaseException("Error: Failed to save record to entity [Reservation]", ex);
        }
        if (Boolean.FALSE.equals(reservationDone.isPresent())) {
            throw new BusinessException("Error: Failed to persist database service");
        }
        return reservationDone.get();
    }

    public BigDecimal getRefundValue(Reservation reservation) {
        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), reservation.getSchedule().getStartDateTime());

        if (hours > 24) {
            return reservation.getValue();
        }

        if(hours >= 12 && hours < 24){
            return reservation.getValue().multiply(new BigDecimal(0.75));
        }

        if(hours >= 2 && hours < 12){
            return reservation.getValue().multiply(new BigDecimal(0.50));
        }

        if(hours > 0 && hours < 2){
            return reservation.getValue().multiply(new BigDecimal(0.25));
        }

        return BigDecimal.ZERO;
    }
}
