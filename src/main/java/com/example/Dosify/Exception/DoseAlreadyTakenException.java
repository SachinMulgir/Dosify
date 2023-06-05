package com.example.Dosify.Exception;

public class DoseAlreadyTakenException extends RuntimeException {

    public DoseAlreadyTakenException(String message){
        super(message);
    }
}
