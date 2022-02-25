package com.tenniscourts.domain.tenniscourts.service;

import com.tenniscourts.domain.schedules.model.Schedule;
import com.tenniscourts.domain.schedules.service.FindScheduleService;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import com.tenniscourts.domain.tenniscourts.model.TennisCourtRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindTennisCourtService {

    private final TennisCourtRepository tennisCourtRepository;

    private final FindScheduleService findScheduleService;

    public TennisCourt findTennisCourtById(Long id) {
        return tennisCourtRepository
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException("Tennis Court not found."));
    }

    public TennisCourt findTennisCourtWithSchedulesById(Long tennisCourtId) {
        TennisCourt tennisCourt = findTennisCourtById(tennisCourtId);
        List<Schedule> ss = findScheduleService.byTennisCourtId(tennisCourtId);
        System.out.println(ss.size());
        tennisCourt.setTennisCourtSchedules(ss);
        return tennisCourt;
    }
}
