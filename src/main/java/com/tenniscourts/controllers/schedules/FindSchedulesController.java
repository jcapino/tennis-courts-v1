package com.tenniscourts.controllers.schedules;

import com.tenniscourts.domain.schedules.dto.ScheduleDTO;
import com.tenniscourts.domain.schedules.mapper.ScheduleMapper;
import com.tenniscourts.domain.schedules.service.FindScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/schedules")
@Api("Controller that allows you to consult the available schedules")
public class FindSchedulesController {

    private final ScheduleMapper scheduleMapper;
    private final FindScheduleService findScheduleService;

    @GetMapping("/findById")
    @ApiOperation("Allows you to consult the information of the time schedule slot of a tennis court filtered by scheduleId")
    public ResponseEntity<ScheduleDTO> findById(@RequestParam("id") Long scheduleId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleMapper.map(findScheduleService.findById(scheduleId)));
    }

    @GetMapping("/findAvailable")
    @ApiOperation("Allows you to consult the free slots according to the start and end dates")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleMapper.map(
                        findScheduleService.byStartDateTimeAndEndDateTime(startDate, endDate)
                ));
    }
}
