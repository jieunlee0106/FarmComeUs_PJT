package com.ssafy.farmcu.exception;

public class NotFoundUserException extends RuntimeException{

    public NotFoundUserException(String message){
        System.out.println(message);
    }
}