package com.tenniscourts.domain.reservations.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CreateReservationRequestDTO {

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Long guestId;

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Long scheduleId;

    @NotNull
    @ApiModelProperty(example = "10", required = true)
    private BigDecimal value;
}
