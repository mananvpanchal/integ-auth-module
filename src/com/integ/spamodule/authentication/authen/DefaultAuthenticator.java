package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.model.Credential;

/**
 * Author: Manan
 * Date: 15-12-2015 04:18
 */

public class DefaultAuthenticator implements Authenticator {

    @Override
    public String authenticate(Object cred) {
        Credential credential = (Credential)cred;

        return null;
    }
}
