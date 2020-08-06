package com.bridgelabs.exception;

public class AddressBookException extends RuntimeException {
    public enum ExceptionType {
        DUPLICATE_RECORD
    }

    public ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
