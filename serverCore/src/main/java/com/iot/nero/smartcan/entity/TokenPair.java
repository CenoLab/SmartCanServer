package com.iot.nero.smartcan.entity;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/15
 * Time   3:18 PM
 */
public class TokenPair implements Serializable {
    private String uniqueid;
    private String token;

    public TokenPair() {
    }

    public TokenPair(String uniqueid, String token) {
        this.uniqueid = uniqueid;
        this.token = token;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenPair{" +
                "uniqueid='" + uniqueid + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
