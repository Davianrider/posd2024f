package com.example;

public class ThirdTestCase extends MyTestCase {
    public ThirdTestCase(String name) {
        super(name);
    }

    public void test59() {
        Assertions.assertEquals(1, 1);
    }

    public void test60() {
        Assertions.assertEquals(1, 2);
    }

    public void test61() {
        Assertions.assertEquals("Hello", "world");
    }
}
