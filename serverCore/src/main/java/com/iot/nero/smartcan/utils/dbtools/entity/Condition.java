package com.iot.nero.smartcan.utils.dbtools.entity;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/9
 * Time   下午1:30
 */
public class Condition implements Serializable {
    private String key;
    private String express;
    private Object value;


    public Condition() {

    }

    public Condition(String key, String express, Object value) {
        this.key = key;
        this.express = express;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "key='" + key + '\'' +
                ", express='" + express + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
