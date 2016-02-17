package com.integ.core.authentication.model;

/**
 * Author: Manan
 * Date: 23-12-2015 18:12
 */

public class TokenPayload {

    protected String iss;
    protected String exp;
    protected String iat;
    protected String usr;
    protected String utp;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getUtp() {
        return utp;
    }

    public void setUtp(String utp) {
        this.utp = utp;
    }
}
