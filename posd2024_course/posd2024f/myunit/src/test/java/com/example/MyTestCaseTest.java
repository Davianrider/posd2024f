package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyTestCaseTest {

    @Test
    public void myTestResult() {
        MyTest myTestCase = new MyTestCase("First Test");
        MyTestResult myTestResult = new MyTestResult();

        myTestCase.run(myTestResult);        

        assertEquals(1, myTestResult.getCount());
        assertEquals("First Test", myTestResult.getTestResultPair(0).getTest().getName());
        assertEquals(UnsupportedOperationException.class, myTestResult.getTestResultPair(0).getResult().getClass());
    }

    @Test
    public void runAnonymousAdapterTestCase() {
        MyTest myTestCase = new FirstTestCase("test19"){
            @Override
            protected void runTest() {
                test19();
            }
        };
        MyTestResult myTestResult = new MyTestResult();

        myTestCase.run(myTestResult);

        assertEquals(1, myTestResult.getCount());
        assertEquals("test19", myTestResult.getTestResultPair(0).getTest().getName());
        assertEquals(null, myTestResult.getTestResultPair(0).getResult());

        MyTest myTestCase2 = new FirstTestCase("test20"){
            @Override
            protected void runTest() {
                test20();
            }
        };

        myTestCase2.run(myTestResult);

        assertEquals(2, myTestResult.getCount());
        assertEquals("test20", myTestResult.getTestResultPair(1).getTest().getName());
        assertEquals(RuntimeException.class, myTestResult.getTestResultPair(1).getResult().getClass());
    }

    @Test
    public void testPluggableSelector() {
        MyTest myTestCase = new FirstTestCase("test19");

        MyTestResult myTestResult = new MyTestResult();

        myTestCase.run(myTestResult);

        MyTest myTestCase2 = new FirstTestCase("test20");

        myTestCase2.run(myTestResult);

        assertEquals(2, myTestResult.getCount());
        assertEquals("test19", myTestResult.getTestResultPair(0).getTest().getName());
        assertEquals(null, myTestResult.getTestResultPair(0).getResult());
        assertEquals("test20", myTestResult.getTestResultPair(1).getTest().getName());
        assertEquals(RuntimeException.class, myTestResult.getTestResultPair(1).getResult().getClass());
    }

    @Test
    public void testTestSuite() {
        MyTestSuite myTestSuite = new MyTestSuite();
        myTestSuite.addTest(new FirstTestCase("test19"));
        myTestSuite.addTest(new FirstTestCase("test20"));

        MyTestResult myTestResult = new MyTestResult();
        myTestSuite.run(myTestResult);

        assertEquals(2, myTestResult.getCount());
        assertEquals("test19", myTestResult.getTestResultPair(0).getTest().getName());
        assertEquals(null, myTestResult.getTestResultPair(0).getResult());
        assertEquals("test20", myTestResult.getTestResultPair(1).getTest().getName());
        assertEquals(RuntimeException.class, myTestResult.getTestResultPair(1).getResult().getClass());
    }

    @Test
    public void testTestSuiteAddTestCase() {
        MyTestSuite myTestSuite = new MyTestSuite();
        myTestSuite.addTestCase(FirstTestCase.class);
        myTestSuite.addTestCase(SecondTestCase.class);

        MyTestResult myTestResult = new MyTestResult();
        myTestSuite.run(myTestResult);

        assertEquals(4, myTestResult.getCount());
        assertEquals("test19", myTestResult.getTestResultPair(0).getTest().getName());
        assertEquals(null, myTestResult.getTestResultPair(0).getResult());
        assertEquals("test20", myTestResult.getTestResultPair(1).getTest().getName());
        assertEquals(RuntimeException.class, myTestResult.getTestResultPair(1).getResult().getClass());
        assertEquals("test39", myTestResult.getTestResultPair(2).getTest().getName());
        assertEquals(null, myTestResult.getTestResultPair(2).getResult());
        assertEquals("test40", myTestResult.getTestResultPair(3).getTest().getName());
        assertEquals(RuntimeException.class, myTestResult.getTestResultPair(3).getResult().getClass());
    }

    @Test
    public void testThirdTestCase() {
        MyTestSuite myTestSuite = new MyTestSuite();
        myTestSuite.addTestCase(ThirdTestCase.class);

        MyTestResult myTestResult = new MyTestResult();
        myTestSuite.run(myTestResult);

        assertEquals(3, myTestResult.getCount());
        assertEquals("test59", myTestResult.getTestResultPair(0).getTest().getName());
        assertEquals(null, myTestResult.getTestResultPair(0).getResult());
        assertEquals("test60", myTestResult.getTestResultPair(1).getTest().getName());
        assertEquals(AssertionFailure.class, myTestResult.getTestResultPair(1).getResult().getClass());
        assertEquals("test61", myTestResult.getTestResultPair(2).getTest().getName());
        assertEquals(AssertionFailure.class, myTestResult.getTestResultPair(2).getResult().getClass());
    }
}
