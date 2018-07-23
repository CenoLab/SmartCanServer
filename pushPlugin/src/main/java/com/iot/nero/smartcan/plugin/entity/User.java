package com.iot.nero.smartcan.plugin.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String uName;
    private String uEmail;
    private String uPhone;


    public User() {
    }

    public User(Integer id, String uName, String uEmail, String uPhone) {
        this.id = id;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPhone = uPhone;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uName='" + uName + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uPhone='" + uPhone + '\'' +
                '}';
    }
}
