package com.tenniscourts.domain.commons.constants;

public enum DateFormats {
    FORMAT_DD_MM_YYYY("dd/MM/yyyy");
    private String format;

    DateFormats(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
