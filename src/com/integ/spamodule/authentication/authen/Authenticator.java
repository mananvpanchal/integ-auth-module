package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.exception.AuthenticationException;
import com.integ.spamodule.authentication.model.User;

/**
 * Author: Manan
 * Date: 15-12-2015 04:07
 */

public interface Authenticator {

    User authenticate(String username, String password) throws AuthenticationException;

}
