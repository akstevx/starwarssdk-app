package com.example.starwarsdk.exceptions;

import java.io.Serializable;

public class Exception extends RuntimeException implements Serializable {

    public Exception(String message) {
        super(message, null);
    }

    public Exception(String message, Throwable e) {
        super(message, e);
    }
}
