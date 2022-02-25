package com.tenniscourts.domain.tenniscourts.dto;

import com.tenniscourts.domain.schedules.dto.ScheduleDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TennisCourtDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @NotNull
    @ApiModelProperty(example = "Sarah's Valley - Court Philippe-Chatrier")
    private String name;

    private List<ScheduleDTO> tennisCourtSchedules;

}
