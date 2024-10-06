package com.bilalbererk.Dukkan.Exceptions;

public class UserNotAuthorizedActionException extends RuntimeException{
    public UserNotAuthorizedActionException(String message){
        super(message);
    }
}
