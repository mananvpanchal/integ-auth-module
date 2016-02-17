package com.integ.core.authentication.authen;

import com.integ.core.authentication.exception.TokenGenerationException;
import com.integ.core.authentication.model.User;
import com.integ.core.properties.util.PropertiesUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Date;

/**
 * Author: Manan
 * Date: 23-12-2015 18:43
 */

public class SignedTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken(User userInfo, int lifeMinute) throws TokenGenerationException {
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

    public PrivateKey getPrivateKey() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, TokenGenerationException {
        String privateKeyPath = PropertiesUtil.getProperty("private-key-path");
        String privateKeyName = PropertiesUtil.getProperty("private-key-name");
        String privateKeyPassword = PropertiesUtil.getProperty("private-key-password");
        if(privateKeyPath == null) {
            throw new TokenGenerationException("private key path is not specified");
        }
        if(privateKeyName == null) {
            throw new TokenGenerationException("private key name is not specified");
        }
        if(privateKeyPassword == null) {
            throw new TokenGenerationException("private key password is not specified");
        }
        FileInputStream inputStream = new FileInputStream(privateKeyPath);
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(inputStream, privateKeyPassword.toCharArray());
        return (PrivateKey) keystore.getKey(privateKeyName, privateKeyPassword.toCharArray());
    }

    public String signToken(PrivateKey privateKey, String encodedTokenHeader, String encodedTokenPayload) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update((encodedTokenHeader + "." + encodedTokenPayload).getBytes());
        byte[] signatureArray = signer.sign();
        return new String(Base64.getEncoder().encode(signatureArray));
    }

}
