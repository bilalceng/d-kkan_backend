package com.bilalbererk.Dukkan.Exceptions;

public class UserDidNotFoundException extends RuntimeException{
    public UserDidNotFoundException(String message){
        super(message);
    }
}
