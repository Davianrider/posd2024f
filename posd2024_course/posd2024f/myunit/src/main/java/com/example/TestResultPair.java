package com.example;

public class TestResultPair {
    private MyTestCase testCase;
    private Throwable result = null;

    public TestResultPair(MyTestCase myTestCase, Throwable result) {
        this.testCase = myTestCase;
        this.result = result;
    }

    public MyTestCase getTest() {
        return testCase;
    }
    
    public Throwable getResult() {
        return result;
    }


}
