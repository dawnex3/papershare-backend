package com.itheima.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResponseData<T> {

    private int code = 200;
    private String message;
    private T data;
    public ResponseData() {
        super();
    }

    public ResponseData(T data) {
        super();
        this.data = data;
    }

    public ResponseData(T data,String message,int code) {
        super();
        this.data = data;
        this.message=message;
        this.code = code;
    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}

