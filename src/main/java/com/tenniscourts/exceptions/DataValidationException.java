package com.tenniscourts.exceptions;

public class DataValidationException extends RuntimeException {
    /**
     * Instantiates a new Business exception.
     *
     * @param msg the msg
     */
    public DataValidationException(String msg){
        super(msg);
    }
    public DataValidationException(String msg, String campo){
        super(String.format(msg, campo));
    }

    private DataValidationException(){}
}
