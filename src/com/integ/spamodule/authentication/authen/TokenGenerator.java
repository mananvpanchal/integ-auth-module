package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.exception.TokenGenerationException;
import com.integ.spamodule.authentication.model.UserInfo;

/**
 * Author: Manan
 * Date: 16-12-2015 07:55
 */

public interface TokenGenerator {

    String generateToken(UserInfo userInfo, int lifeMinute) throws TokenGenerationException;
}
