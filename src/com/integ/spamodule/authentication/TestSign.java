package com.integ.spamodule.authentication;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.util.Base64;
import java.util.Date;

/**
 * Author: Manan
 * Date: 21-12-2015 17:57
 */

public class TestSign {

    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("F:\\Integ\\authen-module\\integ.jks");
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(inputStream, "12345678".toCharArray());
        inputStream.close();
        PrivateKey privateKey = (PrivateKey) keystore.getKey("integ", "12345678".toCharArray());

        String tokenHeader = "{\"typ\":\"JWT\",\"alg\":\"HS256\"}";
        long issuingTime = new Date().getTime();
        long expirationTime = issuingTime + 30 * 60 * 1000;
        String tokenPayload = "{\"iss\":\"INTEG\",\"exp\":\"" + expirationTime + "\",\"iat\":\"" + issuingTime + "\",\"usr\",\"manan\",\"usertype\":\"admin\"}";
        String encodedTokenHeader = new String(Base64.getEncoder().encode(tokenHeader.getBytes()));
        System.out.println("Encoded header: "+encodedTokenHeader);
        String encodedTokenPayload = new String(Base64.getEncoder().encode(tokenPayload.getBytes()));
        System.out.println("Encoded payload: "+encodedTokenPayload);
        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(privateKey);
        signer.update((encodedTokenHeader + "." + encodedTokenPayload).getBytes());
        byte[] signatureArray = signer.sign();
        //System.out.println(new String(signatureArray));
        System.out.println("Encoded sign: "+new String(Base64.getEncoder().encode(signatureArray)));

        /*inputStream = new FileInputStream("F:\\Integ\\authen-module\\integ.cer");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        java.security.cert.Certificate cert = cf.generateCertificate(inputStream);
        inputStream.close();
        PublicKey publicKey = cert.getPublicKey();

        signer = Signature.getInstance("SHA1withRSA");
        signer.initVerify(publicKey);
        signer.update(signStr.getBytes("UTF-8"));

        System.out.println(signer.verify(signatureArray));*/

        /*Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec("12345678".getBytes(), "HmacSHA256"));
        byte[] result = mac.doFinal("Manan Panchal".getBytes());
        System.out.println(new String(result));
        System.out.println(new String(Base64.getEncoder().encode(result)));*/
    }
}
