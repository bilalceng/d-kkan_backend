package com.bilalbererk.Dukkan.Exceptions;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(String message){
        super(message);
    }
}
