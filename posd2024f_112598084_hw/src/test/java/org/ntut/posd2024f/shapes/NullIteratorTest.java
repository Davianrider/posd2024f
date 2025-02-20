package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class NullIteratorTest {
    @Test
    public void hasNextTest(){
        NullIterator nullIterator = new NullIterator();
        boolean hasNextTest = nullIterator.hasNext();
        assertFalse(hasNextTest);
    }

    
    @Test
    public void nextText(){
        Exception exception = assertThrows(Exception.class, () -> {
            new NullIterator().next();
        });
        assertEquals("Null iterator does not point to any element", exception.getMessage());
    }
}