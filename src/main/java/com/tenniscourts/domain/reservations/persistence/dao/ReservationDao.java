package com.tenniscourts.domain.reservations.persistence.dao;

import com.tenniscourts.domain.reservations.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT res " +
            "FROM Reservation as res " +
            "WHERE res.schedule.id = :scheduleId " +
            "AND res.reservationStatus = com.tenniscourts.domain.reservations.model.ReservationStatus.READY_TO_PLAY")
    Optional<Reservation> findByActiveReservationStatusAndSchedule_Id(@Param("scheduleId") Long scheduleId);
}
