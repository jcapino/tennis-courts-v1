package com.tenniscourts.domain.commons.validator;

import java.util.Objects;

public class DataStatusValidator {

    private DataStatusValidator(){
        super();
    }

    public static Boolean isEmptyValue(Object value) {
        return Objects.isNull(value) && Boolean.TRUE.equals("".equals(String.valueOf(value)));
    }
}
