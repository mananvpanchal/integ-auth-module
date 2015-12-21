package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.exception.TokenGenerationException;
import com.integ.spamodule.authentication.model.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Date;

/**
 * Author: Manan
 * Date: 16-12-2015 07:56
 */

public class DefaultTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken(UserInfo userInfo, int lifeMinute) throws TokenGenerationException {
        try {
            String tokenHeader = "{\"typ\":\"JWT\",\"alg\":\"RS256\"}";
            long issuingTime = new Date().getTime();
            long expirationTime = issuingTime + lifeMinute * 60 * 1000;
            String tokenPayload = "{\"iss\":\"INTEG\",\"exp\":\"" + expirationTime + "\",\"iat\":\"" + issuingTime + "\",\"usr\",\"" + userInfo.getUsername() + "\",\"usertype\":\"" + userInfo.getUserType() + "\"}";
            String encodedTokenHeader = new String(Base64.getEncoder().encode(tokenHeader.getBytes()));
            String encodedTokenPayload = new String(Base64.getEncoder().encode(tokenPayload.getBytes()));
            PrivateKey privateKey = getPrivateKey();
            String encodedSignature = signToken(privateKey, encodedTokenHeader, encodedTokenPayload);
            return encodedTokenHeader + "." + encodedTokenPayload + "." + encodedSignature;
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | InvalidKeyException | SignatureException ex) {
            throw new TokenGenerationException("Error generating token", ex);
        }
    }

    public PrivateKey getPrivateKey() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        FileInputStream inputStream = new FileInputStream("F:\\Integ\\authen-module\\integ.jks");
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(inputStream, "12345678".toCharArray());
        return (PrivateKey) keystore.getKey("integ", "12345678".toCharArray());
    }

    public String signToken(PrivateKey privateKey, String encodedTokenHeader, String encodedTokenPayload) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update((encodedTokenHeader + "." + encodedTokenPayload).getBytes());
        byte[] signatureArray = signer.sign();
        return new String(Base64.getEncoder().encode(signatureArray));
    }

}
