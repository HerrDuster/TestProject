package com.comparus.exception;

public class MultithreadingTaskFailedException extends RuntimeException {

    public MultithreadingTaskFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
