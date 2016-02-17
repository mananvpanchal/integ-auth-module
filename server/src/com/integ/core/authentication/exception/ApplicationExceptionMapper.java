package com.integ.core.authentication.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Author: Manan
 * Date: 21-12-2015 16:13
 */

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        ApplicationError error=new ApplicationError();
        error.setMessage(exception.getMessage());
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        PrintStream printStream=new PrintStream(outputStream);
        exception.printStackTrace(printStream);
        String stackTrace=outputStream.toString();
        error.setStackTrace(stackTrace);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
