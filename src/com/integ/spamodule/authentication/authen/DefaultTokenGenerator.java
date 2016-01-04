package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.exception.TokenGenerationException;
import com.integ.spamodule.authentication.model.User;
import com.integ.spamodule.properties.util.PropertiesUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 * Author: Manan
 * Date: 16-12-2015 07:56
 */

public class DefaultTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken(User user, int lifeMinute) throws TokenGenerationException {
        try {
            String tokenHeader = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
            long issuingTime = new Date().getTime();
            long expirationTime = issuingTime + lifeMinute * 60 * 1000;
            String tokenPayload = "{\"iss\":\"INTEG\",\"exp\":\"" + expirationTime + "\",\"iat\":\"" + issuingTime + "\",\"usr\",\"" + user.getUsername() + "\",\"usertype\":\"" + user.getUserType() + "\"}";
            String encodedTokenHeader = new String(Base64.getEncoder().encode(tokenHeader.getBytes()));
            String encodedTokenPayload = new String(Base64.getEncoder().encode(tokenPayload.getBytes()));

            String hmacSecret = PropertiesUtil.getProperty("hmac-secret");
            if(hmacSecret == null) {
                throw new TokenGenerationException("HMAC Secret is not specified");
            }
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(hmacSecret.getBytes(), "HmacSHA256"));
            byte[] hmacBytes = mac.doFinal((encodedTokenHeader + "." + encodedTokenPayload).getBytes());
            String encodedSign = new String(Base64.getEncoder().encode(hmacBytes));
            return encodedTokenHeader + "." + encodedTokenPayload + "." + encodedSign;
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            throw new TokenGenerationException("Error generating token", ex);
        }
    }

}
