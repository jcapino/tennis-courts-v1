package com.tenniscourts.controllers.reservations;

import com.tenniscourts.domain.reservations.dto.ReservationDTO;
import com.tenniscourts.domain.reservations.mapper.ReservationMapper;
import com.tenniscourts.domain.reservations.service.FindReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservation")
@AllArgsConstructor
public class FindByIdReservationController {

    private FindReservationService findReservationService;
    private ReservationMapper reservationMapper;

    @GetMapping("/findById")
    public ResponseEntity<ReservationDTO> findById(@RequestParam("reservationId") Long reservationId) {
        return ResponseEntity.ok(
                reservationMapper.map(
                        findReservationService.findById(reservationId)
                )
        );
    }
}
