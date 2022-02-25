package com.tenniscourts.controllers.guests;

import com.tenniscourts.domain.guests.dto.GuestDto;
import com.tenniscourts.domain.guests.mapper.GuestMapper;
import com.tenniscourts.domain.guests.service.FindGuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/guests")
@Api("Controller that allows to find all guests")
public class FindGuestsController {

    private GuestMapper guestMapper;
    private FindGuestService findGuestService;

    @GetMapping("/findAll")
    @ApiOperation("Service to find all guests")
    public ResponseEntity<List<GuestDto>> findAll() {
        return ResponseEntity.ok(guestMapper.mapList(findGuestService.findAll()));
    }

    @GetMapping("/findByName")
    @ApiOperation("Service to find a guest filter by name")
    public ResponseEntity<List<GuestDto>> findGuestByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(guestMapper.mapList(findGuestService.findByName(name)));
    }

    @GetMapping("/findById")
    @ApiOperation("Service to find a guest filter by id")
    public ResponseEntity<GuestDto> findGuest(
            @RequestParam(value = "guestId") Long guestId) {

        return ResponseEntity.ok(guestMapper.map(findGuestService.findById(guestId)));
    }
}
