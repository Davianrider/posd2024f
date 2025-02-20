package com.example;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HybridContent implements Content {
    private List<Content> contents;

    public HybridContent(List<Content> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Content content : contents) {
            sb.append(content.toString());
        }
        return sb.toString();
    }

    @Override
    public Iterator<Content> iterator() {
        return contents.listIterator();
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visitHybridContent(this);
    }

    @Override
    public void insert(Content picture, Iterator<Content> iterator) {
        ((ListIterator<Content>)iterator).add(picture);
    }

    @Override
    public void remove(Iterator<Content> iterator) {
        iterator.remove();
    }
}
