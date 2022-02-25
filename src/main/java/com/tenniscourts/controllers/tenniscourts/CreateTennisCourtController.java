package com.tenniscourts.controllers.tenniscourts;

import com.tenniscourts.config.rest.BaseRestController;
import com.tenniscourts.domain.tenniscourts.dto.TennisCourtDTO;
import com.tenniscourts.domain.tenniscourts.handler.TennisCourtAdditionHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@RequestMapping
@AllArgsConstructor
public class CreateTennisCourtController extends BaseRestController {

    private final TennisCourtAdditionHandler tennisCourtAdditionHandler;

    //TODO: implement rest and swagger
    @PostMapping
    public ResponseEntity<URI> addTennisCourt(TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationByEntity(tennisCourtAdditionHandler.run(tennisCourtDTO).getId()));
    }
}
