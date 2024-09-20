package com.neoteric.javaFullStack.exception;

public class AccountCreationFailedException extends Exception{

    public String message;
    public AccountCreationFailedException (String message){
        this.message=message;

    }
}
