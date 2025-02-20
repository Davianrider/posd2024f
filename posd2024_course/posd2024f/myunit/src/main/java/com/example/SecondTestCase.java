package com.example;

public class SecondTestCase extends MyTestCase {
    public SecondTestCase(String name) {
        super(name);
    }

    public void test39() {
        // success
    }

    public void test40() {
        throw new RuntimeException("Test40 Failed");
    }
}
