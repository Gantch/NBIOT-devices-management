package com.gantch.nbiotdevicesmanagement.dao.exception;

/**
 * @author lcw332
 * Date 2019-12-24-13:24
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao.util
 **/
public class DataValidationException extends RuntimeException {

    private static final long serialVersionUID = 7659985660312721830L;

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}