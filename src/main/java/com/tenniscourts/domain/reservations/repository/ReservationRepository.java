package com.tenniscourts.domain.reservations.repository;

import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository  {

    Optional<Reservation> save(Reservation reservation);
    Optional<List<Reservation>> findAll();
    Optional<Reservation> findById(Long reservationId);
    Optional<Reservation> cancelReservationById(Reservation reservation);

    Optional<Reservation> findByReservationStatusReadyToPlayAndSchedule_Id(Long scheduleId);
    List<Reservation> findByReservationStatusAndSchedule_StartDateTimeGreaterThanEqualAndSchedule_EndDateTimeLessThanEqual(ReservationStatus reservationStatus, LocalDateTime startDateTime, LocalDateTime endDateTime);

//    List<Reservation> findByStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqualAndTennisCourt(LocalDateTime startDateTime, LocalDateTime endDateTime, TennisCourt tennisCourt);
}
