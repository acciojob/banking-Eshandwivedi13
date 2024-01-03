package com.driver;

public class InSufficientBalanceException extends Exception{
    public InSufficientBalanceException(String message) {
        super(message);
    }
}
