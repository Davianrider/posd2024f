package com.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NullIterator implements Iterator<Content> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Content next() {
        throw new NoSuchElementException();
    }

}
