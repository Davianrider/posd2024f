package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContentClipboardTest {
    
    @Test
    public void getSameInstance() {
        ContentClipboard clipboard1 = ContentClipboard.getInstance();
        ContentClipboard clipboard2 = ContentClipboard.getInstance();

        assertTrue(clipboard1 == clipboard2);
    }

    @Test
    public void copyText() {
        ContentClipboard clipboard = ContentClipboard.getInstance();
        clipboard.copy(new Text("Hello, World!"));

        Content content = clipboard.paste();

        assertEquals("Hello, World!", content.toString());
    }
}
