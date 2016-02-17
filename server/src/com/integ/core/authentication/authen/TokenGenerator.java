package com.integ.core.authentication.authen;

import com.integ.core.authentication.exception.TokenGenerationException;
import com.integ.core.authentication.model.User;

/**
 * Author: Manan
 * Date: 16-12-2015 07:55
 */

public interface TokenGenerator {

    String generateToken(User user, int lifeMinute) throws TokenGenerationException;
}
