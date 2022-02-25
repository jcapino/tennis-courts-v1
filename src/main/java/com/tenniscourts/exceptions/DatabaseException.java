package com.tenniscourts.exceptions;

public class DatabaseException extends RuntimeException{

    /**
     * Instantiates a new Database exception.
     *
     * @param msg the msg
     */
    public DatabaseException(String msg){
        super(msg);
    }

    public DatabaseException(String msg, Throwable throwable){
        super(msg, throwable);
    }

    private DatabaseException(){}
}
