package com.integ.spamodule.authentication.authen;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashUtil {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int SALT_BYTE_SIZE = 32;
    public static final int HASH_BYTE_SIZE = 32;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static String generateSalt() throws NoSuchAlgorithmException{
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return toHex(salt);
    }

    public static String createHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createHash(password.toCharArray(), salt);
    }


    public static String createHash(char[] password, String saltStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = fromHex(saltStr);
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        return toHex(hash);
    }

    public static boolean validatePassword(String password, String salt, String passwordHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.toCharArray(), salt, passwordHash);
    }


    public static boolean validatePassword(char[] password, String saltStr, String passwordHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = fromHex(saltStr);
        byte[] hash = fromHex(passwordHash);
        byte[] testHash = pbkdf2(password, salt, PBKDF2_ITERATIONS, hash.length);
        return slowEquals(hash, testHash);
    }


    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }


    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }


    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }


    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }


    public static void main(String[] args) throws Exception {
        String salt = generateSalt();
        System.out.println(salt);
        String hash = createHash("test123", salt);
        System.out.println(hash);
        boolean validated=validatePassword("test123", salt, hash);
        System.out.println(validated);
    }

}