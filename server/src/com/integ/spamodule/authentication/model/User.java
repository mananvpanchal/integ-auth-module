package com.integ.spamodule.authentication.model;

/**
 * Author: Manan
 * Date: 23-12-2015 14:10
 */

public class User extends Credential {

    protected String firstName;
    protected String lastName;
    protected String userType;
    protected boolean authenticated;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
