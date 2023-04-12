package com.ssafy.farmcu.exception;

public class NotFoundStoreException extends RuntimeException{
    public NotFoundStoreException(String message) {
        System.out.println(message);
    }
}
