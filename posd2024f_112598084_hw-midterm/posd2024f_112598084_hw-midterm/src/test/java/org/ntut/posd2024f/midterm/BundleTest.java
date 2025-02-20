package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.Iterator;

import org.junit.jupiter.api.Test;


public class BundleTest {

    @Test
    public void bundleInitest(){
        Exception exception = assertThrows(Exception.class, () -> {
            new Bundle("");
        });
        
        assertEquals("The bundle should have a title.", exception.getMessage());
    }

    @Test 
    public void getTitleTest(){
        Bundle bundle = new Bundle("happy");
        assertEquals("happy", bundle.getTitle());
    }

    @Test
    public void addBookTest(){
        Bundle bundle = new Bundle("happy");
        Book book = new Book("Effective Java", 45.0);
        bundle.add(book);
        assertEquals("Effective Java", bundle.items.get(0).getTitle());
    }

    @Test
    public void addBundleTest(){
        Bundle bundle = new Bundle("happy");
        Bundle bundle2 = new Bundle("happy2");
        bundle.add(bundle2);
        assertEquals("happy2", bundle.items.get(0).getTitle());
    }

    
    @Test
    public void testIteratorThrowsNoSuchElementException() {
        Bundle bundle = new Bundle("Test Bundle");
        Iterator<Item> iterator = bundle.iterator();
        assertThrows(NoSuchElementException.class, () -> {
            while (true) {
                iterator.next();
            }
        });
    }
}
