package com.example.BankingApp.exception;

public class NotValid extends RuntimeException{
    public NotValid(String message) {
        super(message);
    }
}
