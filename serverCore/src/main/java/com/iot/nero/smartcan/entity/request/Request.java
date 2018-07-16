package com.iot.nero.smartcan.entity.request;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/6
 * Time   1:56 PM
 */
public class Request<T>  implements Serializable{
    private String requestId;
    private Byte requestType;
    private T data;

    public Request() {
    }

    public Request(String requestId, Byte requestType, T data) {
        this.requestId = requestId;
        this.requestType = requestType;
        this.data = data;
    }

    public Byte getRequestType() {
        return requestType;
    }

    public void setRequestType(Byte requestType) {
        this.requestType = requestType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", requestType=" + requestType +
                ", data=" + data +
                '}';
    }
}
