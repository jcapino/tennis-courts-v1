package com.tenniscourts.domain.commons;

public enum H2ErrorCodes {
    UNIQUE_CONSTRAINT(1);

    private Integer code;

    H2ErrorCodes(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
