package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class VisitorTest {
    @Test
    public void testWordCountingVisitorWithText() {
        Content text = new Text("Hello, world!");
        WordCountingVisitor visitor = new WordCountingVisitor("Hello");
        text.accept(visitor);
        assertEquals(1, visitor.getWordCount());
    }

    @Test
    public void testWordCountingWithoutGivenText() {
        Content text = new Text("Hello, world!");
        WordCountingVisitor visitor = new WordCountingVisitor();
        text.accept(visitor);
        assertEquals(2, visitor.getWordCount());
    }

    @Test
    public void testWordCountingVisitorWithPicture() {
        Content picture = new Picture("src/test/resources/blue_image.png");
        WordCountingVisitor visitor = new WordCountingVisitor();
        picture.accept(visitor);
        assertEquals(1000, visitor.getWordCount());
    }

    @Test
    public void testWordCountingVisitorWithPicture2() {
        Content picture = new Picture("src/test/resources/blue_image.png");
        WordCountingVisitor visitor = new WordCountingVisitor("Hello");
        picture.accept(visitor);
        assertEquals(0, visitor.getWordCount());
    }

    @Test
    public void testWordCountingVisitorWithCaptionedContent() {
        Content picture = new Picture("src/test/resources/blue_image.png");
        
        Text caption = new Text("This is a blue image");

        CaptionedContent captionedPicture = new CaptionedContent(picture, caption);

        WordCountingVisitor visitor = new WordCountingVisitor();
        captionedPicture.accept(visitor);
        assertEquals(1005, visitor.getWordCount());
    }

    @Test
    public void testWordCountingVisitorWithCaptionedHybridContent() {
        Content red_picture = new Picture("src/test/resources/red_image.png");
        Text red_caption = new Text("This is a red image");
        CaptionedContent captionedRedPicture = new CaptionedContent(red_picture, red_caption);

        Content blue_picture = new Picture("src/test/resources/blue_image.png");
        Text blue_caption = new Text("This is a blue image");
        CaptionedContent captionedBluePicture = new CaptionedContent(blue_picture, blue_caption);

        List<Content> contents = new ArrayList<>();
        contents.add(captionedRedPicture);
        contents.add(captionedBluePicture);

        HybridContent redAndBlue = new HybridContent(contents);

        CaptionedContent redAndBlueWithCaption = new CaptionedContent(redAndBlue, new Text("red and blue images"));

        WordCountingVisitor visitor = new WordCountingVisitor();

        redAndBlueWithCaption.accept(visitor);

        assertEquals(2014, visitor.getWordCount());
    }

    @Test
    public void testFindVisitorWithText() {
        Text text = new Text("Hello, world!");
        FindVisitor visitor = new FindVisitor("world");
        text.accept(visitor);
        assertEquals(7, text.getPosition());

        InsertVisitor insertVisitor = new InsertVisitor(new Text("beautiful "));
        text.accept(insertVisitor);
        assertEquals(1, insertVisitor.getResult().size());
        assertEquals("Hello, beautiful world!", insertVisitor.getResult().get(0).toString());
    }

    @Test
    public void testFindVisitorWithPicture() {
        Text text = new Text("Hello, world!");
        FindVisitor visitor = new FindVisitor("world");
        text.accept(visitor);

        Picture picture = new Picture("src/test/resources/blue_image.png");
        InsertVisitor insertVisitor = new InsertVisitor(picture);
        text.accept(insertVisitor);

        assertEquals(3, insertVisitor.getResult().size());
        assertEquals("Hello, ", insertVisitor.getResult().get(0).toString());
        assertEquals(picture.toString(), insertVisitor.getResult().get(1).toString());
        assertEquals("world!", insertVisitor.getResult().get(2).toString());
    }
}
