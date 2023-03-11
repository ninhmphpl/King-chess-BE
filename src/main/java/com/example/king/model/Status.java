package com.example.king.model;

public enum Status {
    WAITING(1),
    READY(2),
    PLAYING(3);

    private final int code;
    Status(Integer code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
