package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.model.AuthenInfo;

/**
 * Author: Manan
 * Date: 15-12-2015 04:07
 */

public interface Authenticator {

    boolean authenticate(String username, String password);

}
