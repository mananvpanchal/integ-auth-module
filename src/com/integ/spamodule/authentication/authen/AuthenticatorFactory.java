package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.exception.AuthenticationException;

/**
 * Author: Manan
 * Date: 15-12-2015 04:35
 */

public class AuthenticatorFactory {

    protected static AuthenticatorFactory factory;
    protected Authenticator authenticator;

    private AuthenticatorFactory() {

    }

    public static AuthenticatorFactory getFactory() {
        if(factory == null) {
            synchronized (AuthenticatorFactory.class) {
                if(factory == null) {
                    factory = new AuthenticatorFactory();
                }
            }
        }
        return factory;
    }

    public Authenticator getAuthenticator() throws AuthenticationException {
        if(authenticator == null) {
            synchronized (this) {
                if(authenticator == null) {
                    String authenticatorStr = System.getProperty("authenticator");
                    if(authenticatorStr == null) {
                        authenticator = new DefaultAuthenticator();
                    } else {
                        try {
                            authenticator = (Authenticator) Class.forName(authenticatorStr).newInstance();
                        } catch (Exception ex) {
                            throw new AuthenticationException("Exception in loading authenticator", ex);
                        }
                    }
                }
            }
        }
        return authenticator;
    }
}
