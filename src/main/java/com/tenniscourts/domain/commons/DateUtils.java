package com.tenniscourts.domain.commons;

import com.tenniscourts.domain.commons.constants.DateFormats;
import com.tenniscourts.exceptions.DataValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDate stringToSimpleDateFormatLocalDate(String dateValue) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormats.FORMAT_DD_MM_YYYY.getFormat());

            return LocalDate.parse(dateValue, formatter);
        }catch (Exception e){
            throw new DataValidationException("Error: It is not possible to process the format for the entered date");
        }
    }
}
