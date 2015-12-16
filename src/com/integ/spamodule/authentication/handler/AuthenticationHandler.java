package com.integ.spamodule.authentication.handler;

import com.integ.spamodule.authentication.authen.AuthenticatorFactory;
import com.integ.spamodule.authentication.exception.AuthenticationException;
import com.integ.spamodule.authentication.model.AuthenInfo;
import com.integ.spamodule.authentication.model.Credential;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Author: Manan
 * Date: 15-12-2015 04:27
 */
@Path("open/dologin")
public class AuthenticationHandler {

    private static final Logger LOG=Logger.getLogger(AuthenticationHandler.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String doLogin(Credential cred) {
        String returnValue=null;
        try {
            boolean authenticated = AuthenticatorFactory.getFactory().getAuthenticator().authenticate(cred.getUsername(), cred.getPassword());
            if(authenticated) {
                returnValue = "token";
            }
        } catch (AuthenticationException ex) {
            LOG.error("Error during authentication / token generation", ex);
            returnValue = "login_failure";
        }
        return returnValue;
    }
}
