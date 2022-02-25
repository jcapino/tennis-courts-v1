package com.tenniscourts.domain.schedules.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateScheduleRequestDTO {

    @NotNull
    @ApiModelProperty(example = "1")
    private Long tennisCourtId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    @ApiModelProperty(example = "2020-02-21T03:00")
    private LocalDateTime startDateTime;

}
