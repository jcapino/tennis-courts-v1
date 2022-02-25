package com.tenniscourts.controllers.tenniscourts;

import com.tenniscourts.config.rest.BaseRestController;
import com.tenniscourts.domain.tenniscourts.dto.TennisCourtDTO;
import com.tenniscourts.domain.tenniscourts.handler.TennisCourtQueriesHandler;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
public class FindTennisCourtController extends BaseRestController {

    private final TennisCourtQueriesHandler tennisCourtQueriesHandler;

    @GetMapping("/findById")
    @ApiOperation("Service that allows you to search for tennis courts filtered by id")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(Long tennisCourtId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tennisCourtQueriesHandler.findTennisCourtById(tennisCourtId));
    }

    @GetMapping("/findAvailablesById")
    @ApiOperation("Service that allows you to consult available schedules filtered by the tennis court id")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtQueriesHandler.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
