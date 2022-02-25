package com.tenniscourts.domain.guests.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class GuestDto {

    private Long guestId;

    @NotNull
    private String name;
}
