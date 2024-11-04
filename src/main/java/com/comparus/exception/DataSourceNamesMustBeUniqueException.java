package com.comparus.exception;

public class DataSourceNamesMustBeUniqueException extends RuntimeException {

    private static final String MESSAGE = "Database names must be unique!";

    public DataSourceNamesMustBeUniqueException() {
        super(MESSAGE);
    }
}
