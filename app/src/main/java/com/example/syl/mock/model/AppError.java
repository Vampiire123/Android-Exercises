package com.example.syl.mock.model;

public class AppError implements AbsError {

    String msg;

    public AppError(String msg) {
        this.msg = msg;
    }

    @Override
    public String message() {
        return msg;
    }
}