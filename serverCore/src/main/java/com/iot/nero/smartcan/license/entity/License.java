package com.iot.nero.smartcan.license.entity;

import com.iot.nero.smartcan.constant.LicenseType;

public class License {

    private LicenseType licenseType;
    private String key;
    private String value;


    public License(LicenseType licenseType, String key, String value) {
        this.licenseType = licenseType;
        this.key = key;
        this.value = value;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "License{" +
                "licenseType=" + licenseType +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
