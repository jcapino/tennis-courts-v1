package com.tenniscourts.controllers.guests;

import com.tenniscourts.config.rest.BaseRestController;
import com.tenniscourts.domain.guests.dto.GuestDto;
import com.tenniscourts.domain.guests.mapper.GuestMapper;
import com.tenniscourts.domain.guests.service.CreateGuestService;
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
@RequestMapping("/v1/guests")
@Api("Controller that allows to create guests")
public class CreateGuestController extends BaseRestController {

    private CreateGuestService createGuestService;
    private GuestMapper guestMapper;

    @PostMapping
    @ApiOperation("Service to create a new guest")
    public ResponseEntity<URI> createGuest(@RequestBody GuestDto guestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationByEntity(createGuestService.run(guestMapper.map(guestDto)).getId()));
    }
}
