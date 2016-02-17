package com.integ.core.authentication;

/**
 * Author: Manan
 * Date: 04-01-2016 14:41
 */

public class TestSplit {

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJTlRFRyIsImV4cCI6IjE0NTE5MDAzOTI3NDQiLCJpYXQiOiIxNDUxODk4NTkyNzQ0IiwidXNyIiwiYWRtaW4iLCJ1c2VydHlwZSI6ImFkbWluIn0=.NFufk3j5YgOsO1XuyvcadLEjzLA+yX3xj5meNiwCLKI=";
        String[] tokenParts = token.split("\\.");
        System.out.println(tokenParts[0]);
        System.out.println(tokenParts[1]);
        System.out.println(tokenParts[2]);
    }
}
