package com.example;

import java.util.ArrayList;
import java.util.List;

public class MyTestResult {
    private int runCount = 0;
    private List<TestResultPair> testResultPairs = new ArrayList<>();
    
    public void startTest() {
        runCount++;
    }

    public int getCount() {
        return runCount;
    }

    public TestResultPair getTestResultPair(int i) {
        return testResultPairs.get(i);
    }

    public void addFailure(MyTestCase myTestCase, Throwable e) {
        TestResultPair testResultPair = new TestResultPair(myTestCase, e);

        testResultPairs.add(testResultPair);
    }

    public void addSuccess(MyTestCase myTestCase) {
        TestResultPair testResultPair = new TestResultPair(myTestCase, null);

        testResultPairs.add(testResultPair);
    }
}
