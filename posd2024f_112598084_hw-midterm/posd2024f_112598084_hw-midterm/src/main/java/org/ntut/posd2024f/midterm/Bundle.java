package org.ntut.posd2024f.midterm;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class Bundle implements Item, Iterable<Item>{

    private String title;
    public List<Item> items = new ArrayList<Item>();

    public Bundle(String title) throws IllegalArgumentException{
        if (title == null || title.trim().isEmpty())
        throw new IllegalArgumentException("The bundle should have a title.");

        this.title = title;
    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public void add(Item item){
        if (item.getTitle() == null || item.getTitle().trim().isEmpty())
            throw new IllegalArgumentException("The bundle should have a title.");

        items.add(item);
    }

    @Override
    public <T> void accept(ItemVisitor<T> visitor) {
        visitor.visitBundle(this);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ItemIterator(items);
    }

    private class ItemIterator implements Iterator<Item> {
        private List<Item> items;
        private int currentIndex = 0;

        public ItemIterator(List<Item> items) {
            this.items = items;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < items.size();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = items.get(currentIndex++);
            if (item instanceof Bundle) {
                // If the item is a bundle, we want to iterate over its items recursively
                Bundle bundle = (Bundle) item;
                return bundle;  // Return the bundle itself
            }
            return item;
        }
    }
}