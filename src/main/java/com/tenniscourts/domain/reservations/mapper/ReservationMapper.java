package com.tenniscourts.domain.reservations.mapper;

import com.tenniscourts.domain.reservations.dto.CreateReservationRequestDTO;
import com.tenniscourts.domain.reservations.dto.ReservationDTO;
import com.tenniscourts.domain.reservations.dto.ReservationStatusDTO;
import com.tenniscourts.domain.reservations.dto.UpdateReservationRequestDTO;
import com.tenniscourts.domain.reservations.model.Reservation;
import com.tenniscourts.domain.reservations.model.ReservationStatus;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    Reservation map(ReservationDTO source);

    @InheritInverseConfiguration
    ReservationDTO map(Reservation source);

    @Mapping(target = "guest.id", source = "guestId")
    @Mapping(target = "schedule.id", source = "scheduleId")
    Reservation map(CreateReservationRequestDTO source);

    @Mapping(target = "guest.id", source = "guestId")
    @Mapping(target = "schedule.id", source = "scheduleId")
    @Mapping(target = "id", source = "reservationId")
    Reservation map(UpdateReservationRequestDTO source);

    @InheritInverseConfiguration
    default List<ReservationDTO> mapList(List<Reservation> reservations){
        if(CollectionUtils.isEmpty(reservations)){
            return Collections.emptyList();
        }
        return reservations.stream().map(reservation -> map(reservation)).collect(Collectors.toList());
    }

    @ValueMapping(source = "CANCELLED", target = "CANCELLED")
    @ValueMapping(source = "RESCHEDULED", target = "RESCHEDULED")
    @ValueMapping(source = "COMPLETED", target = "COMPLETED")
    ReservationStatus reservationStatusStringToReservationStatus(ReservationStatusDTO reservationStatusDTO);
}
