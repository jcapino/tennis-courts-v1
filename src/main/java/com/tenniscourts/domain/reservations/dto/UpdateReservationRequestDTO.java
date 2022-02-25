package com.tenniscourts.domain.reservations.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UpdateReservationRequestDTO {

    @ApiModelProperty(example = "1")
    private Long guestId;

    @ApiModelProperty(example = "1")
    private Long reservationId;

    @ApiModelProperty(example = "2")
    private Long scheduleId;

    @NotNull
    @ApiModelProperty(example = "CANCELED", allowableValues = "[\"CANCELLED\",\"RESCHEDULED\",\"COMPLETED\"]")
    private String reservationStatus;
}
