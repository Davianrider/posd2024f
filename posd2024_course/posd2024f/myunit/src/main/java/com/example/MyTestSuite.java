package com.example;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyTestSuite implements MyTest {
    private List<MyTest> tests = new ArrayList<MyTest>();

    public MyTestSuite() {
    }

    @Override
    public void run(MyTestResult myTestResult) {
        for (MyTest test : tests) {
            test.run(myTestResult);
        }
    }

    public void addTest(MyTest test) {
        tests.add(test);
    }

    public <T extends MyTestCase> void addTestCase(Class<T> class1) {
        List<String> testNames = new ArrayList<String>();
        for (Method m : class1.getDeclaredMethods()) {
            if (m.getName().startsWith("test")) {
                testNames.add(m.getName());
            }
        }
        testNames.sort(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        for (String testName : testNames) {
            try {
                MyTestCase testCase = class1.getConstructor(String.class).newInstance(testName);
                addTest(testCase);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
