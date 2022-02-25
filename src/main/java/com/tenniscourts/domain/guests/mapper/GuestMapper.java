package com.tenniscourts.domain.guests.mapper;

import com.tenniscourts.domain.guests.dto.GuestDto;
import com.tenniscourts.domain.guests.model.Guest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GuestMapper {
    Guest map(GuestDto guestDto);

    @InheritInverseConfiguration
    GuestDto map(Guest guestDto);

    @InheritInverseConfiguration
    default List<GuestDto> mapList(List<Guest> guests){
        if(CollectionUtils.isEmpty(guests)){
            return Collections.emptyList();
        }
        return guests.stream().map(guest -> map(guest)).collect(Collectors.toList());
    }
}
