package com.iot.nero.smartcan.exceptions;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/18
 * Time   10:23 AM
 */
public class PackageBrokenException extends RuntimeException {
    public PackageBrokenException() {
    }

    public PackageBrokenException(String message) {
        super(message);
    }
}
