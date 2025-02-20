package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTestCase implements MyTest {
    private String name;

    public MyTestCase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public final void run(MyTestResult myTestResult) {
        myTestResult.startTest();
        setUp();
        try {
            runTest();
            myTestResult.addSuccess(this);
        } catch (Throwable e) {
            myTestResult.addFailure(this, e);
        }
        tearDown();
    }

    protected void setUp() {
    }

    protected void runTest() throws Throwable {
        // Use Java Reflection to find the method with the given name
        // and invoke it
        Method m = null;
        try {
            m = this.getClass().getDeclaredMethod(this.name);
        } catch (NoSuchMethodException e) {
            throw new UnsupportedOperationException("Unimplemented method 'runTest'");
        }
        try {
            m.invoke(this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw e.getCause();
        }
    }

    protected void tearDown() {
    }

}
