package com.tenniscourts.domain.tenniscourts.handler;

import com.tenniscourts.domain.tenniscourts.dto.TennisCourtDTO;
import com.tenniscourts.domain.tenniscourts.handler.mapper.TennisCourtMapper;
import com.tenniscourts.domain.tenniscourts.service.FindTennisCourtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TennisCourtQueriesHandler {

    private final FindTennisCourtService findTennisCourtService;
    private final TennisCourtMapper tennisCourtMapper;

    public TennisCourtDTO findTennisCourtById(Long tennisCourtId){
        return tennisCourtMapper.map(findTennisCourtService.findTennisCourtById(tennisCourtId));
    }

    public TennisCourtDTO findTennisCourtWithSchedulesById(Long tennisCourtId){
        return tennisCourtMapper.map(findTennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
