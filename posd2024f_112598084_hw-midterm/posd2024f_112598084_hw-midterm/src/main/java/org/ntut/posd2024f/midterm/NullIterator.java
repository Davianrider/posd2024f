package org.ntut.posd2024f.midterm;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NullIterator implements Iterator<Item> {
    @Override
    public boolean hasNext(){
        return false;
    }

    @Override
    public Item next() throws NoSuchElementException{
        throw new NoSuchElementException("No more element.");
    }
}