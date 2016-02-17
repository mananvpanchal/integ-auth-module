package com.integ.core.authentication.authen;

import com.integ.core.authentication.exception.AuthenticationException;

/**
 * Author: Manan
 * Date: 21-12-2015 13:00
 */

public class TokenGeneratorFactory {

    protected static TokenGeneratorFactory factory;
    protected TokenGenerator tokenGenerator;

    private TokenGeneratorFactory() {

    }

    public static TokenGeneratorFactory getFactory() {
        if(factory == null) {
            synchronized (TokenGeneratorFactory.class) {
                if(factory == null) {
                    factory = new TokenGeneratorFactory();
                }
            }
        }
        return factory;
    }

    public TokenGenerator getTokenGenerator() throws AuthenticationException {
        if(tokenGenerator == null) {
            synchronized (this) {
                if(tokenGenerator == null) {
                    String authenticatorStr = System.getProperty("authenticator");
                    if(authenticatorStr == null) {
                        tokenGenerator = new DefaultTokenGenerator();
                    } else {
                        try {
                            tokenGenerator = (TokenGenerator) Class.forName(authenticatorStr).newInstance();
                        } catch (Exception ex) {
                            throw new AuthenticationException("Exception in loading token generator", ex);
                        }
                    }
                }
            }
        }
        return tokenGenerator;
    }
}
