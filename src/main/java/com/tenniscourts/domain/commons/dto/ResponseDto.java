package com.tenniscourts.domain.commons.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class ResponseDto {

    private LocalDateTime timestamp;
    private String message;
    private List<String> details;
}
