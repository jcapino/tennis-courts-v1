package com.tenniscourts.domain.commons;

import java.sql.SQLException;

public enum DatabaseMessages {
    ENTITY_NOT_FOUND("Error: Entity with id %s not found");

    private String message;

    DatabaseMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String translateMessage(Throwable ex) {
        if (ex.getCause() == null) {
            return null;
        }

        SQLException sqlException = (SQLException) ex.getCause().getCause();
        int vendorCode = sqlException.getErrorCode();

        if(vendorCode == H2ErrorCodes.UNIQUE_CONSTRAINT.getCode()){
            return "The record you are trying to create already exists in the database";
        }
        return ex.getMessage();
    }
}
