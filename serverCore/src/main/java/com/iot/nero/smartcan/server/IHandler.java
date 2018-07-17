package com.iot.nero.smartcan.server;

import com.iot.nero.smartcan.exceptions.PackageBrokenException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/5
 * Time   8:49 PM
 */
public interface IHandler {

    void readProcess() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, PackageBrokenException;

    void writeProcess() throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

}
