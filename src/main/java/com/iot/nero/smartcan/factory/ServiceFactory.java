package com.iot.nero.smartcan.factory;

import net.sf.cglib.reflect.FastClass;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/7/15
 * Time   2:46 PM
 */
public class ServiceFactory {

    static HashMap<FastClass,Object> fastclassObjectHashMap = new HashMap<>();

    public static Object getService(FastClass fastClass) throws InvocationTargetException {
        if(fastclassObjectHashMap.get(fastClass)==null){
            Object obj = fastClass.newInstance();
            fastclassObjectHashMap.put(fastClass,obj);
            return obj;
        }else {
            return fastclassObjectHashMap.get(fastClass);
        }

    }
}
