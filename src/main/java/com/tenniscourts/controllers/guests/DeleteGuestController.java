package com.tenniscourts.controllers.guests;

import com.tenniscourts.domain.guests.mapper.GuestMapper;
import com.tenniscourts.domain.guests.service.DeleteGuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/guests")
@Api("Controller that allows to delete guests")
public class DeleteGuestController {

    private GuestMapper guestMapper;
    private DeleteGuestService deleteGuestService;

    @DeleteMapping("/{guestId}")
    @ApiOperation("")
    public ResponseEntity<Void> deleteGuest(@PathVariable("guestId") Long guestId) {
        deleteGuestService.run(guestId);
        return ResponseEntity.noContent().build();
    }
}
