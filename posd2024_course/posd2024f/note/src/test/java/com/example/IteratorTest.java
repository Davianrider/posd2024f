package com.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorTest {

    @Test
    public void testIterator() {
        // Step 1: Create a list of integers
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        // Step 2: Obtain an iterator from the list
        Iterator<Integer> iterator = numbers.iterator();

        // Step 3: Use a loop to iterate through the list using the iterator
        int index = 0;
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            // Step 4: Use JUnit assertions to verify the iteration
            assertEquals(numbers.get(index), number);
            index++;
        }
    }

    @Test
    public void testNullIterator() {
        // when I get an iterator from a text, it should return a null iterator
        Content text = new Text("Hello World!");
        Iterator<Content> iterator = text.iterator();
        assertFalse(iterator.hasNext());
        assertThrows(Exception.class, () -> iterator.next());
    }
}