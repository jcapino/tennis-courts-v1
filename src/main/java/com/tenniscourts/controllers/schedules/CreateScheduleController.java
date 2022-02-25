package com.tenniscourts.controllers.schedules;

import com.tenniscourts.config.rest.BaseRestController;
import com.tenniscourts.domain.schedules.dto.CreateScheduleRequestDTO;
import com.tenniscourts.domain.schedules.mapper.ScheduleMapper;
import com.tenniscourts.domain.schedules.service.CreateScheduleService;
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
@RequestMapping("/v1/schedules")
@AllArgsConstructor
@Api("Controller that allows to create a schedule for a given tennis court")
public class CreateScheduleController extends BaseRestController {

    private final CreateScheduleService createScheduleService;
    private final ScheduleMapper scheduleMapper;

    @PostMapping
    @ApiOperation("Service that allows you to create schedule slots for a given tennis court")
    public ResponseEntity<URI> addTennisCourtSchedule(
            @RequestBody CreateScheduleRequestDTO createScheduleRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        locationByEntity(
                                this.scheduleMapper.map(
                                        createScheduleService.run(
                                                createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO.getStartDateTime()
                                        )
                                ).getId()));
    }
}
