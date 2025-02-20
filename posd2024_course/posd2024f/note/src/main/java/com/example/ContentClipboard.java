package com.example;

public class ContentClipboard {
    private static ContentClipboard instance = null;
    private Content instanceData = null;
    
    private ContentClipboard() {
    }

    public static ContentClipboard getInstance() {
        if (instance == null) {
            instance = new ContentClipboard();
        }
        return instance;
    }

    public void copy(Content content) {
        instanceData = content;
    }

    public Content paste() {
        return instanceData;
    }
}
