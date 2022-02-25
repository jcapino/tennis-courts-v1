package com.tenniscourts.domain.tenniscourts.handler;

import com.tenniscourts.domain.tenniscourts.dto.TennisCourtDTO;
import com.tenniscourts.domain.tenniscourts.handler.mapper.TennisCourtMapper;
import com.tenniscourts.domain.tenniscourts.service.CreateTennisCourtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TennisCourtAdditionHandler {

    private final CreateTennisCourtService createTennisCourtService;
    private final TennisCourtMapper tennisCourtMapper;

    public TennisCourtDTO run(TennisCourtDTO tennisCourtDTO){

        return tennisCourtMapper.map(
                createTennisCourtService.run(tennisCourtMapper.map(tennisCourtDTO))
        );
    }
}
