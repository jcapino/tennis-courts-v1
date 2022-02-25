package com.tenniscourts.domain.tenniscourts.handler.mapper;

import com.tenniscourts.domain.tenniscourts.model.TennisCourt;
import com.tenniscourts.domain.tenniscourts.dto.TennisCourtDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TennisCourtMapper {
    TennisCourtDTO map(TennisCourt source);

    @InheritInverseConfiguration
    TennisCourt map(TennisCourtDTO source);
}
