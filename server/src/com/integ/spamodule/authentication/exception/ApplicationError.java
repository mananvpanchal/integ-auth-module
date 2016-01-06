package com.integ.spamodule.authentication.exception;

/**
 * Author: Manan
 * Date: 21-12-2015 16:14
 */

public class ApplicationError {

    protected String message;
    protected String stackTrace;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
