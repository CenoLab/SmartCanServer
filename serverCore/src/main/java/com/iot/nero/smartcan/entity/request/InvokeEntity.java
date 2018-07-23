package com.iot.nero.smartcan.entity.request;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/6
 * Time   10:51 AM
 */
public class InvokeEntity implements Serializable {

    private Class<?> aClass;
    private String methodName;
    private Class<?>[] parameterType;
    private Object[] args;

    public InvokeEntity() {
    }

    public InvokeEntity(Class<?> aClass, String methodName, Class<?>[] parameterType, Object[] args) {
        this.aClass = aClass;
        this.methodName = methodName;
        this.parameterType = parameterType;
        this.args = args;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class<?>[] parameterType) {
        this.parameterType = parameterType;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "InvokeEntity{" +
                "aClass=" + aClass +
                ", methodName='" + methodName + '\'' +
                ", parameterType=" + Arrays.toString(parameterType) +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
