package com.tenniscourts.domain.reservations.persistence;

import com.tenniscourts.domain.reservations.persistence.dao.ReservationDao;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import com.tenniscourts.domain.reservations.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ReservationRepositoryH2 implements ReservationRepository {

    private final ReservationDao reservationDao;

    @Override
    public Optional<Reservation> save(Reservation reservation) {
        return Optional.of(reservationDao.save(reservation));
    }

    @Override
    public Optional<List<Reservation>> findAll() {
        return Optional.ofNullable(reservationDao.findAll());
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        return reservationDao.findById(reservationId);
    }

    @Override
    public Optional<Reservation> cancelReservationById(Reservation reservation) {

        return Optional.of(reservationDao.save(reservation));
    }

    @Override
    public Optional<Reservation> findByReservationStatusReadyToPlayAndSchedule_Id(Long scheduleId) {
        return reservationDao.findByActiveReservationStatusAndSchedule_Id(scheduleId);
    }

    @Override
    public List<Reservation> findByReservationStatusAndSchedule_StartDateTimeGreaterThanEqualAndSchedule_EndDateTimeLessThanEqual(ReservationStatus reservationStatus, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return null;
    }
}
