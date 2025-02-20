package com.example;

public class FirstTestCase extends MyTestCase {
    public FirstTestCase(String name) {
        super(name);
    }

    public void test19() {
        // success
    }

    public void test20() {
        throw new RuntimeException("Test20 Failed");
    }
}
