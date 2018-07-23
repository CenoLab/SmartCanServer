package com.iot.nero.smartcan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/13
 * Time   10:57 AM
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceMethod {
    byte value();
}
