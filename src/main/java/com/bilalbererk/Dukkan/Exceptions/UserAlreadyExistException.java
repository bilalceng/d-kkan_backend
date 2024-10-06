package com.bilalbererk.Dukkan.Exceptions;

import org.aspectj.bridge.IMessage;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message){
        super(message);
    }
}

