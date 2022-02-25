package com.tenniscourts.controllers.reservations;

import com.tenniscourts.config.rest.BaseRestController;
import com.tenniscourts.domain.reservations.dto.ReservationDTO;
import com.tenniscourts.domain.reservations.mapper.ReservationMapper;
import com.tenniscourts.domain.reservations.service.FindReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/reservation")
@AllArgsConstructor
public class FindAllReservationController extends BaseRestController {

    private FindReservationService findReservationService;
    private ReservationMapper reservationMapper;

    @GetMapping("/findAll")
    public ResponseEntity<List<ReservationDTO>> findAll() {
        return ResponseEntity.ok(
                reservationMapper.mapList(
                        findReservationService.findAll()
                )
        );
    }
}
