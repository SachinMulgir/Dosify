package com.example.Dosify.Exception;

public class DoctorNotPresentException extends RuntimeException{

    public DoctorNotPresentException(String msg){
        super(msg);
    }
}
