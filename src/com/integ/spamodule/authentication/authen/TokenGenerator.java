package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.model.AuthenInfo;

/**
 * Author: Manan
 * Date: 16-12-2015 07:55
 */

public interface TokenGenerator {

    String generateToken(String username);
}
