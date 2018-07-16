package com.iot.nero.smartcan.entity.response;

import java.io.Serializable;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/6
 * Time   1:56 PM
 */
public class Response<T> implements Serializable {
    private String responseId;
    private boolean status;
    private T data;
    private String msg;


    public Response() {
    }

    public Response(String responseId, boolean status, T data) {
        this.responseId = responseId;
        this.status = status;
        this.data = data;
    }

    public Response(String responseId, boolean status, T data, String msg) {
        this.responseId = responseId;
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseId='" + responseId + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
