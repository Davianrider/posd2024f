package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;



public class BookTest {    

    @Test
    public void BookTestInit(){
        Exception exception1 = assertThrows(Exception.class, () -> {
            new Book("", 100);
        });
        
        assertEquals("The book should have a title.", exception1.getMessage());
        
        Exception exception2 = assertThrows(Exception.class, () -> {
            new Book("123", -10);
        });

        assertEquals("The price should be greater than or equal to 0.", exception2.getMessage());
    }

    @Test
    public void getTitleTest(){
        Book book = new Book("happy", 200);
        assertEquals("happy", book.getTitle());
    }

    @Test
    public void getPrivceTest(){
        Book book = new Book("happy", 200);
        assertEquals(200, book.getPrice());
    }


    @Test
    public void testBookInitializationWithValidArguments() {
        Book book = new Book("Effective Java", 45.0);
        assertEquals("Effective Java", book.getTitle());
        assertEquals(45.0, book.getPrice());
    }

    @Test
    public void testBookInitializationWithInvalidTitle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("", 45.0);
        });
        assertEquals("The book should have a title.", exception.getMessage());
    }

    @Test
    public void testBookInitializationWithInvalidPrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("Effective Java", -10.0);
        });
        assertEquals("The price should be greater than or equal to 0.", exception.getMessage());
    }

    @Test
    public void testGetTitle() {
        Book book = new Book("Clean Code", 50.0);
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    public void testGetPrice() {
        Book book = new Book("Clean Code", 50.0);
        assertEquals(50.0, book.getPrice());
    }
}