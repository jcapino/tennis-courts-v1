package com.tenniscourts.domain.schedules.persistence.dao;

import com.tenniscourts.domain.schedules.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleDao extends JpaRepository<Schedule, Long> {

    @Query(value = "SELECT sch " +
            "FROM Schedule sch " +
            "WHERE sch.id NOT IN (SELECT res.schedule.id " +
            "FROM Reservation res " +
            "WHERE res.reservationStatus = com.tenniscourts.domain.reservations.model.ReservationStatus.CANCELLED)")
    List<Schedule> findAllAvailableSchedules();
    @Query(value = "SELECT sch " +
            "FROM Schedule sch " +
            "WHERE sch.startDateTime >= :startDate AND sch.endDateTime <= :endDate " +
            "AND sch.id NOT IN (SELECT res.schedule.id " +
            "FROM Reservation res " +
            "WHERE res.reservationStatus != com.tenniscourts.domain.reservations.model.ReservationStatus.CANCELLED) " +
            "ORDER BY sch.startDateTime ASC")
    List<Schedule> findByTennisCourtStartDateTimeAndEndDateTime_OrderByStartDateTime(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT sch " +
            "FROM Schedule sch " +
            "WHERE sch.tennisCourt.id = :tennisCourtId " +
            "AND :startDateTime >= sch.startDateTime and :startDateTime < sch.endDateTime")
    Schedule findByTennisCourtIdAndStartDateTimeIsNotCrossedWithAnotherTime(@Param("tennisCourtId") Long tennisCourtId, @Param("startDateTime") LocalDateTime startDateTime);

    @Query(value = "SELECT sch " +
            "FROM Schedule as sch " +
            "WHERE sch.tennisCourt.id = :tennisCourtId " +
            "AND sch.id not in (select res.schedule.id " +
            "FROM Reservation as res)")
    List<Schedule> findByTennisCourt_Id(@Param("tennisCourtId") Long tennisCourtId);
}
