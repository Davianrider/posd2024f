package org.ntut.posd2024f.midterm;

import java.util.Iterator;

public interface Item {
    public String getTitle();

    default void add(Item item) {
    }

    default Iterator<Item> iterator() {
    }

    default Iterator<Item> dfsIterator() {
    }
    
    public <T> void accept(ItemVisitor<T> visitor);
}
