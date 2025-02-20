package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class NoteTest {
    @Test
    void testNoteCreation() {
        Note note = new Note();
        note.addContent(new Text("Hello World!"));
        note.addContent(new Text("Goodbye World!"));
        assertEquals("Hello World!\nGoodbye World!\n", note.toString());
    }

    @Test
    void testNoteCreationWithHybridContent() {
        Note note = new Note();

        List<Content> contents = new ArrayList<>();
        contents.add(new Text("Hello,"));
        contents.add(new Text(" World!"));
        Content hybrid = new HybridContent(contents);

        note.addContent(hybrid);
        note.addContent(new Text("Goodbye World!"));

        assertEquals("Hello, World!\nGoodbye World!\n", note.toString());
    }
}
