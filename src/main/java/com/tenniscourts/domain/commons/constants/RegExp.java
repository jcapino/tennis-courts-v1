package com.tenniscourts.domain.commons.constants;

public enum RegExp {

    SIMPLE_TIME_FORMAT("[0-9]{1,2}:[0-9]{1,2}"),
    SIMPLE_DATE_FORMAT("[0-9]{1}[0-9]{1}/[0-9]{1}[0-9]{1}/[0-9]{4}");

    private String regExp;

    RegExp(String regExp) {
        this.regExp = regExp;
    }

    public String getRegExp() {
        return regExp;
    }
}
