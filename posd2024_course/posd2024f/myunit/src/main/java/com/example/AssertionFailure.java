package com.example;

public class AssertionFailure extends RuntimeException {
    public AssertionFailure(String message) {
        super(message);
    }
}
