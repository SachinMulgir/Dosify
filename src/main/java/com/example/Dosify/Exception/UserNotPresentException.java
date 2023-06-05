package com.example.Dosify.Exception;

public class UserNotPresentException extends RuntimeException{

    public UserNotPresentException(String message){
        super(message);
    }
}
