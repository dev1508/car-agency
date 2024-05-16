package com.example.agency.exception;

import lombok.Data;

@Data
public class ErrorException {
    private int status;
    private String message;

    public ErrorException(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
