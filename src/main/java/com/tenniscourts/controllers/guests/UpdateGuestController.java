package com.tenniscourts.controllers.guests;

import com.tenniscourts.domain.guests.dto.GuestDto;
import com.tenniscourts.domain.guests.mapper.GuestMapper;
import com.tenniscourts.domain.guests.service.UpdateGuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/guests")
@Api("Controller that allows to update guest information")
public class UpdateGuestController {

    private GuestMapper guestMapper;
    private UpdateGuestService updateGuestService;

    @PatchMapping
    @ApiOperation("Service to update a guest")
    public ResponseEntity<GuestDto> updateGuest(@RequestBody GuestDto guestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        guestMapper.map(updateGuestService.run(guestMapper.map(guestDto)))
                );
    }
}
