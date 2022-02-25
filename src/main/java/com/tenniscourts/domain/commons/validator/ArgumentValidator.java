package com.tenniscourts.domain.commons.validator;

import com.tenniscourts.exceptions.DataValidationException;

import java.math.BigDecimal;
import java.util.Objects;

public class ArgumentValidator extends RuntimeException {

    private ArgumentValidator() {
        super();
    }

    public static void isRequiredField(Object value, String field) {
        if (Objects.isNull(value)) {
            throw new DataValidationException(String.format("The value of the %s field is required", field));
        }
    }

    public static void isEmptyValue(Object value, String field) {
        if (Objects.isNull(value) || Boolean.TRUE.equals("".equals(String.valueOf(value)))) {
            throw new DataValidationException(String.format("The value of the %s field cannot be null or empty", field));
        }
    }

    public static void isTheValueLessThanTheMinimumLimit(BigDecimal limitValue, BigDecimal value, String field){
        if(limitValue.compareTo(value) > 0){
            throw new DataValidationException(String.format("The value of the %s field can NOT be less than %s", field, limitValue));
        }
    }
}
