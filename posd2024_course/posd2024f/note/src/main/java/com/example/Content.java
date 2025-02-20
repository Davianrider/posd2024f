package com.example;

import java.util.Iterator;

public interface Content {
    default Iterator<Content> iterator() {
        return new NullIterator();
    }

    default void insert(Content picture, Iterator<Content> iterator) {
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    default void remove(Iterator<Content> iterator) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    default void accept(ContentVisitor visitor) {
    }
}
