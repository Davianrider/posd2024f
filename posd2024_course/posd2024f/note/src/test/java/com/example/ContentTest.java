package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ContentTest {
    
    @Test
    void testTextCreation() {
        Content text = new Text("Hello World!");
        assertEquals("Hello World!", text.toString());
    }

    @Test
    public void hybridContentCreation() {
        Content text1 = new Text("Hello,");
        Content text2 = new Text(" World!");

        List<Content> contents = new ArrayList<>();
        contents.add(text1);
        contents.add(text2);

        Content hybridContent = new HybridContent(contents);

        assertEquals("Hello, World!", hybridContent.toString());
    }

    @Test
    public void accessContentOfHybridContent() {
        Content text1 = new Text("Hello,");
        Content text2 = new Text(" World!");

        List<Content> contents = new ArrayList<>();
        contents.add(text1);
        contents.add(text2);

        HybridContent hybridContent = new HybridContent(contents);

        Iterator<Content> iterator = hybridContent.iterator();

        assertEquals("Hello,", iterator.next().toString());
        assertEquals(" World!", iterator.next().toString());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testTextReturnEmptyIterator() {
        Content text = new Text("Hello World!");
        Iterator<Content> iterator = text.iterator();
        assertEquals(null, iterator);
    }

    // add a test for creating a Picture content that uses the pngj library where the file is blue_image.png
    // and the metadata is verified

    @Test
    public void testPictureCreation() {
        Picture picture = new Picture("src/test/resources/blue_image.png");
        
        assertEquals("blue_image.png", picture.getValue("File name"));

    }

    @Test
    public void testPictureGetImageData() throws FileNotFoundException {
        Picture picture = new Picture("src/test/resources/blue_image.png");
        int[][] imageData = new int[100][300];
        picture.loadImageData();
        imageData = picture.getImageData();

        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                assertEquals(0, imageData[row][col*3]);
                assertEquals(0, imageData[row][col*3 + 1]);
                assertEquals(255, imageData[row][col*3 + 2]);
            }
        }
    }

    @Test
    public void testACaptionedPicture() {
        Content picture = new Picture("src/test/resources/blue_image.png");
        
        Text caption = new Text("This is a blue image");

        CaptionedContent captionedPicture = new CaptionedContent(picture, caption);

        assertEquals("This is a blue image", captionedPicture.getCaption().toString());
        assertEquals("blue_image.png", ((Picture) captionedPicture.getContent()).getValue("File name"));
    }

    @Test
    public void testHybridContentWithCaptionedContent() throws FileNotFoundException {
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

        CaptionedContent redAndBlueWithCaption = new CaptionedContent(redAndBlue, new Text("Red and Blue images"));

        Iterator<Content> it = redAndBlueWithCaption.getContent().iterator();
        assertEquals("This is a red image", ((CaptionedContent) it.next()).getCaption().toString());
        assertEquals("This is a blue image", ((CaptionedContent) it.next()).getCaption().toString());
        assertFalse(it.hasNext());
    }

    @Test
    public void testWordCount() {
        Content text = new Text("Hello, World!");

        WordCountingVisitor visitor = new WordCountingVisitor();

        text.accept(visitor);

        assertEquals(2, visitor.getWordCount());
    }

    @Test
    public void testPictureWordCount() throws FileNotFoundException {
        Picture picture = new Picture("src/test/resources/blue_image.png");

        WordCountingVisitor visitor = new WordCountingVisitor();

        picture.accept(visitor);

        assertEquals(1000, visitor.getWordCount());
    }

    @Test
    public void testCaptionedContentWordCount() throws FileNotFoundException {
        Content picture = new Picture("src/test/resources/blue_image.png");
        Content text = new Text("Hello, world!");

        List<Content> contents = new ArrayList<>();
        contents.add(picture);
        contents.add(text);

        HybridContent hybridContent = new HybridContent(contents);

        WordCountingVisitor visitor = new WordCountingVisitor();

        hybridContent.accept(visitor);

        assertEquals(1002, visitor.getWordCount());
    }

    @Test
    public void testHybridContentWithCaptionedContentWordCount() throws FileNotFoundException {
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

        CaptionedContent redAndBlueWithCaption = new CaptionedContent(redAndBlue, new Text("Red and Blue images"));

        WordCountingVisitor visitor = new WordCountingVisitor();

        redAndBlueWithCaption.accept(visitor);

        assertEquals(2014, visitor.getWordCount());
    }

    @Test
    public void testWordFrequency() {
        Text text = new Text("Hello! Hello! This is a big hello to you!");

        WordCountingVisitor visitorCountHello = new WordCountingVisitor("Hello");
        WordCountingVisitor visitorCounthello = new WordCountingVisitor("hello");

        text.accept(visitorCountHello);
        text.accept(visitorCounthello);

        assertEquals(2, visitorCountHello.getWordCount());
        assertEquals(1, visitorCounthello.getWordCount());
    }

    @Test
    public void testHybridContentWithCaptionedContentWordFrequency() throws FileNotFoundException {
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

        WordCountingVisitor visitorCountRed = new WordCountingVisitor("red");
        WordCountingVisitor visitorCountBlue = new WordCountingVisitor("blue");


        redAndBlueWithCaption.accept(visitorCountRed);
        redAndBlueWithCaption.accept(visitorCountBlue);

        assertEquals(2, visitorCountRed.getWordCount());
        assertEquals(2, visitorCountBlue.getWordCount());
    }

    // add a test for inserting a picture into a hybrid content with two text contents
    @Test
    public void testInsertPictureIntoHybridContent() throws FileNotFoundException {
        Content text1 = new Text("Hello,");
        Content text2 = new Text(" World!");

        List<Content> contents = new ArrayList<>();
        contents.add(text1);
        contents.add(text2);

        Content hybridContent = new HybridContent(contents);

        Content picture = new Picture("src/test/resources/blue_image.png");

        Iterator<Content> iterator = hybridContent.iterator();

        iterator.next();

        hybridContent.insert(picture, iterator);

        assertEquals("Hello,blue_image.png World!", hybridContent.toString());
    }

    // repeat the previous test but with remove method in hybrid content
    @Test
    public void testRemovePictureFromHybridContent() throws FileNotFoundException {
        Content text1 = new Text("Hello,");
        Content text2 = new Text(" World!");

        List<Content> contents = new ArrayList<>();
        contents.add(text1);
        contents.add(text2);

        Content hybridContent = new HybridContent(contents);

        Iterator<Content> iterator = hybridContent.iterator();

        iterator.next();

        hybridContent.remove(iterator);

        assertEquals(" World!", hybridContent.toString());
    }

}
