package com.iot.nero.smartcan.license.core;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
/**
 * 单例模式下的证书管理器
 * @author yang
 */
public class LicenseManagerHolder
{
    private static LicenseManager licenseManager;
    private LicenseManagerHolder(){}
    public static synchronized  LicenseManager getLicenseManager(LicenseParam param)
    {
        if(licenseManager==null)
        {
            licenseManager=new LicenseManager(param);
        }
        return licenseManager;
    }
}

