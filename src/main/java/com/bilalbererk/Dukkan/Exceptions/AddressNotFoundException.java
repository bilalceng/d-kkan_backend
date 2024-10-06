package com.bilalbererk.Dukkan.Exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message){
        super(message);
    }
}
