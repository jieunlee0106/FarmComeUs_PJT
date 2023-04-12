package com.ssafy.farmcu.exception;

public class OutOfStockException  extends RuntimeException{
    public OutOfStockException(String message){
        super(message);
    }
}
