package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.domain.commons.DatabaseMessages;
import com.tenniscourts.domain.commons.validator.ArgumentValidator;
import com.tenniscourts.exceptions.AlreadyExistsEntityException;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.domain.guests.repository.GuestsRepository;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.schedules.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookReservationService extends BaseReservationService {

    private final GuestsRepository guestsRepository;
    private final ScheduleRepository scheduleRepository;

    public Reservation run(Reservation reservation) {

        validateReservationBeforeSave(reservation);

        return save(reservation);
    }

    private void validateReservationBeforeSave(Reservation reservation) {

        ArgumentValidator.isRequiredField(reservation.getSchedule(), "Schedule");
        ArgumentValidator.isRequiredField(reservation.getGuest().getId(), "Guest Id");
        ArgumentValidator.isRequiredField(reservation.getSchedule().getId(), "Schedule Id");
        ArgumentValidator.isRequiredField(reservation.getValue(), "Deposit Value");

        ArgumentValidator.isEmptyValue(reservation.getGuest().getId(), "Guest Id");
        ArgumentValidator.isEmptyValue(reservation.getSchedule().getId(), "Schedule Id");
        ArgumentValidator.isEmptyValue(reservation.getValue(), "Deposit Value");

        ArgumentValidator.isTheValueLessThanTheMinimumLimit(new BigDecimal(10), reservation.getValue(), "Deposit Value");

        Optional<Reservation> reservationAlreadyExists = reservationRepository.findByReservationStatusReadyToPlayAndSchedule_Id(reservation.getSchedule().getId());

        if (Boolean.TRUE.equals(reservationAlreadyExists.isPresent())) {
            throw new AlreadyExistsEntityException("Error: There is already a reservation for the indicated date and time");
        }
        guestsRepository.findById(reservation.getGuest().getId()).orElseThrow(() -> new EntityNotFoundException(DatabaseMessages.ENTITY_NOT_FOUND.getMessage(), reservation.getGuest().getId()));
        scheduleRepository.findById(reservation.getSchedule().getId()).orElseThrow(() -> new EntityNotFoundException(DatabaseMessages.ENTITY_NOT_FOUND.getMessage(), reservation.getSchedule().getId()));
    }
}
