package com.integ.spamodule.authentication.model;

/**
 * Author: Manan
 * Date: 23-12-2015 18:10
 */

public class TokenHeader {

    protected String typ;
    protected String alg;

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }
}
