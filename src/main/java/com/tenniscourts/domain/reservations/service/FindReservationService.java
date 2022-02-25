package com.tenniscourts.domain.reservations.service;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindReservationService {

    private ReservationRepository reservationRepository;

    public List<Reservation> findAll() {
        return reservationRepository.findAll().orElseThrow(()-> new EntityNotFoundException("Not records."));
    }

    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(()-> new EntityNotFoundException("Reservation not found."));
    }
}
