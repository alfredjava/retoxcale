package com.xcale.ecommerce.infrastructure;

public class MyException extends RuntimeException {

    public MyException(String message, String eMessage) {
        super(message, new Throwable(eMessage));
    }
}
