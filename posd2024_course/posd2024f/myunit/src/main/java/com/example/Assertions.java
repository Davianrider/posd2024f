package com.example;

public class Assertions {

    public static <T> void assertEquals(T expected, T actual) {
        if (expected != actual) {
            throw new AssertionFailure("Expected " + expected + " but was " + actual);
        }
    }
}
