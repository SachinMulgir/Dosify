package com.example.Dosify.Exception;

public class DifferentVaccineTypeException extends RuntimeException{
    public DifferentVaccineTypeException(String msg){
        super(msg);
    }
}
