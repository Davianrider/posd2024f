package org.ntut.posd2024f.midterm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Iterator;



public class DiscountItemTest {

    @Test
    public void testDiscountItemCreation() {
        Item item = new Book("PoEAA", 50.0);
        DiscountItem discountItem = new DiscountItem(item, 0.1);
        assertEquals(item, discountItem.getItem());
        assertEquals(0.1, discountItem.getDiscount());
    }

    @Test
    public void testDiscountItemCreationWithInvalidDiscount() {
        Item item = new Book("PoEAA", 50.0);
        assertThrows(IllegalArgumentException.class, () -> new DiscountItem(item, 0));
        assertThrows(IllegalArgumentException.class, () -> new DiscountItem(item, 1));
    }

    @Test
    public void testDiscountBookTitle() {
        Item item = new Book("PoEAA", 50.0);
        DiscountItem discountItem = new DiscountItem(item, 0.1);
        assertEquals("<PoEAA> is on sale! 10% off!", discountItem.getTitle());
    }

    @Test
    public void testIteratorWithBook() {
        Item item = new Book("PoEAA", 50.0);
        DiscountItem discountItem = new DiscountItem(item, 0.1);
        Iterator<Item> iterator = discountItem.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(item, iterator.next());
        assertFalse(iterator.hasNext());
    }
}