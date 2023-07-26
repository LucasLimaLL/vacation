package com.lucaslima.vacation.application.exceptions;

public class BrokenRuleValidationException extends RuntimeException {

    public BrokenRuleValidationException(String message) {
        super(message);
    }
}
