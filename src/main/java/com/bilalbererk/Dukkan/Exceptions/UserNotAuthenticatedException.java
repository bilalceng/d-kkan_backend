package com.bilalbererk.Dukkan.Exceptions;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException(String message){
        super(message);
    }
}
