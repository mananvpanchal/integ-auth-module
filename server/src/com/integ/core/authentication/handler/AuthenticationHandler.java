package com.integ.core.authentication.handler;

import com.integ.core.authentication.authen.AuthenticatorFactory;
import com.integ.core.authentication.authen.TokenGeneratorFactory;
import com.integ.core.authentication.model.Credential;
import com.integ.core.authentication.model.User;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Author: Manan
 * Date: 15-12-2015 04:27
 */
@Path("open/dologin")
public class AuthenticationHandler {

    private static final Logger LOG = Logger.getLogger(AuthenticationHandler.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String doLogin(Credential cred) throws Exception {
        String token = null;
        User user = AuthenticatorFactory.getFactory().getAuthenticator().authenticate(cred.getUsername(), cred.getPassword());
        if (user.isAuthenticated()) {
            LOG.debug("User authenticated");
            token = TokenGeneratorFactory.getFactory().getTokenGenerator().generateToken(user, 30);
        }
        return token;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "Hello!";
    }
}
