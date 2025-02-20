package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BuilderTest {
    @Test
    public void buildText() {
        String string = "Hello, World!";
        Builder builder = new Builder();
        builder.buildText(string);
        List<Content> contents = builder.getContents();

        assertEquals("Hello, World!", contents.get(0).toString());
    }

    @Test
    public void buildHybrid() {
        String string1 = "Hello, ";
        String string2 = "World!";

        Builder builder = new Builder();
        builder.beginHybrid();
        builder.buildText(string1);
        builder.buildText(string2);
        builder.endHybrid();

        List<Content> contents = builder.getContents();

        assertEquals("Hello, World!", contents.get(0).toString());
    }

    @Test
    public void buildNestedHybrid() {
        String string1 = "Hello, ";
        String string2 = "World!";
        String string3 = "Goodbye, ";
        String string4 = "World!";

        Builder builder = new Builder();
        builder.beginHybrid();
        builder.buildText(string1);
        builder.buildText(string2);
        builder.beginHybrid();
        builder.buildText(string3);
        builder.buildText(string4);
        builder.endHybrid();
        builder.endHybrid();

        List<Content> contents = builder.getContents();

        assertEquals("Hello, World!Goodbye, World!", contents.get(0).toString());
    }
}
