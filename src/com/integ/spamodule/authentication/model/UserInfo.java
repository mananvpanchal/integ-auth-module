package com.integ.spamodule.authentication.model;

/**
 * Author: Manan
 * Date: 16-12-2015 07:48
 */

public class UserInfo {

    protected String username;
    protected String userType;
    protected boolean authenticated;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
