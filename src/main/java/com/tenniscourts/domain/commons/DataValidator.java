package com.tenniscourts.domain.commons;

import com.tenniscourts.domain.commons.constants.RegExp;
import com.tenniscourts.exceptions.DataValidationException;

public class DataValidator {

    private DataValidator() {
        super();
    }

    public static void isSimpleDateFormat(Object value, String campo) {
        if(Boolean.FALSE.equals(String.valueOf(value).matches(RegExp.SIMPLE_DATE_FORMAT.getRegExp()))){
            throw new DataValidationException("Error: The %s field contains invalid characters.", campo);
        }
    }

    public static void isSimpleTimeFormat(String value, String campo) {
        if(Boolean.FALSE.equals(String.valueOf(value).matches(RegExp.SIMPLE_TIME_FORMAT.getRegExp()))){
            throw new DataValidationException("Error: The %s field contains invalid characters.", campo);
        }
    }
}
