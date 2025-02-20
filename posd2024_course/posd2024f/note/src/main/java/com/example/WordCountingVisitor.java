package com.example;

import java.util.Iterator;

public class WordCountingVisitor implements ContentVisitor {
    private String theWord;
    private int wordCount;

    public WordCountingVisitor(String word) {
        this.theWord = word;
        this.wordCount = 0;
    }

    public WordCountingVisitor() {
        this.theWord = "";
        this.wordCount = 0;
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public void visitText(Text text) {
        String[] words = text.getText().split("\\W+");
        if (theWord.equals("")) {
            wordCount += words.length;
            return;
        }
        for (String word : words) {
            if (word.equals(theWord)) {
                wordCount++;
            }
        }
    }

    @Override
    public void visitPicture(Picture picture) {
        if (theWord.equals("")) {
            wordCount += 1000;
            return;
        }
        wordCount += 0;
    }

    @Override
    public void visitCaptionedContent(CaptionedContent captionedContent) {
        captionedContent.getContent().accept(this);
        captionedContent.getCaption().accept(this);
    }

    @Override
    public void visitHybridContent(HybridContent hybridContent) {
        Iterator<Content> it = hybridContent.iterator();

        while(it.hasNext()){
            it.next().accept(this);
        }
    }
}


