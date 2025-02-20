package com.example;

public class MyTestRunner {
    public static void main(String[] args) {
        MyTestSuite suite = new MyTestSuite();
        suite.addTestCase(DogFood.class);

        MyTestResult result = new MyTestResult();
        suite.run(result);

        System.out.println("Tests run: " + result.getCount());
        for (int i = 0; i < result.getCount(); i++) {
            TestResultPair testResultPair = result.getTestResultPair(i);
            System.out.print(testResultPair.getTest().getName() + ": ");
            if (testResultPair.getResult() != null) {
                System.out.println("Failed");
                System.out.println(testResultPair.getResult().getMessage());
            } else {
                System.out.println("Success");
            }
        }
        System.out.println();
    }
}
