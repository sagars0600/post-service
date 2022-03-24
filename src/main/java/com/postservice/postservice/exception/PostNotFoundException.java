package com.postservice.postservice.exception;


public class PostNotFoundException  extends RuntimeException{
    public PostNotFoundException(String message) {
        super(message);
    }
}
