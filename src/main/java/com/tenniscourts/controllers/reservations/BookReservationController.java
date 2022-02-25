package com.tenniscourts.controllers.reservations;

import com.tenniscourts.config.rest.BaseRestController;
import com.tenniscourts.domain.reservations.dto.CreateReservationRequestDTO;
import com.tenniscourts.domain.reservations.mapper.ReservationMapper;
import com.tenniscourts.domain.reservations.service.BookReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/reservation")
@Api("Controller that allows you to create a reservation for a tennis court given a schedule.")
public class BookReservationController extends BaseRestController {

    private final ReservationMapper reservationMapper;
    private final BookReservationService bookReservationService;

    @PostMapping
    @ApiOperation("Service that allows you to reserve one or more tennis courts at a given date schedule")
    public ResponseEntity<URI> bookReservation(@RequestBody CreateReservationRequestDTO createReservationRequestDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        locationByEntity(reservationMapper.map(
                                bookReservationService.run(
                                        reservationMapper.map(createReservationRequestDTO)
                                )).getId()
                        )
                );
    }
}
