package com.example;


public class ScannerTest {
    @Test
    public void scanText() {
        ContentScanner scanner = new ContentScanner("note/src/test/resources/test.txt");
        
        String token = scanner.scanToken();
        String content = scanner.scanContent();

        assertEquals("Text", token);
        assertEquals("Hello, World!", content);
    }
}
