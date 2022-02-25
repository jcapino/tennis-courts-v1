package com.tenniscourts.domain.reservations.model;

import org.springframework.core.convert.converter.Converter;


public class StringToReservationStatus implements Converter<String, ReservationStatus> {
    @Override
    public ReservationStatus convert(String source) {
        try{
            return ReservationStatus.valueOf(source);
        }catch (IllegalArgumentException ex){
            return null;
        }
    }
}
