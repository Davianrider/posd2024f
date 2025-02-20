package org.ntut.posd2024f.midterm;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Stack;

public class DFSIterator implements Iterator<Item> {
    private Stack<Iterator<Item>> stack = new Stack<>();

    public DFSIterator(Item item) {
        stack.push(new SingleItemIterator(item));
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<Item> iterator = stack.peek();
            if (iterator.hasNext()) {
                return true;
            } else {
                stack.pop();
            }
        }
        return false;
    }

    @Override
    public Item next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more element.");
        }
        
        Iterator<Item> iterator = stack.peek();
        Item item = iterator.next();

        if (item instanceof Bundle || item instanceof DiscountItem) {
            stack.push(item.iterator());
        }

        return item;
    }

    private static class SingleItemIterator implements Iterator<Item> {
        private Item item;
        private boolean hasBeenReturned = false;

        public SingleItemIterator(Item item) {
            this.item = item;
        }

        @Override
        public boolean hasNext() {
            return !hasBeenReturned;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element.");
            }
            hasBeenReturned = true;
            return item;
        }
    }
}
