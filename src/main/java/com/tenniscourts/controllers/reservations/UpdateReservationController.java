package com.tenniscourts.controllers.reservations;

import com.tenniscourts.domain.reservations.dto.ReservationDTO;
import com.tenniscourts.domain.reservations.dto.UpdateReservationRequestDTO;
import com.tenniscourts.domain.reservations.mapper.ReservationMapper;
import com.tenniscourts.domain.reservations.service.UpdateReservationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/reservation")
@Api("Controller that allows to cancel, reschedule or close a reservation")
public class UpdateReservationController {

    private final UpdateReservationService updateReservationService;
    private final ReservationMapper reservationMapper;

    @PatchMapping
    private ResponseEntity<ReservationDTO> updateReservation(@RequestBody UpdateReservationRequestDTO updateReservationRequestDTO) {
        return ResponseEntity
                .ok(reservationMapper.map(
                                updateReservationService.run(reservationMapper.map(updateReservationRequestDTO))
                        )
                );
    }
}
