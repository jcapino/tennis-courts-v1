package com.tenniscourts.exceptions;

/**
 * The type Entity not found exception.
 */
public class EntityNotFoundException extends RuntimeException {
  /**
   * Instantiates a new Entity not found exception.
   *
   * @param msg the msg
   */
  public EntityNotFoundException(String msg){
        super(msg);
    }

    public EntityNotFoundException(String msg, Object entityId){
        super(String.format(msg, entityId));
    }

    private EntityNotFoundException(){}
}
