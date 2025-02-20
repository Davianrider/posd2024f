package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Builder {
    private List<Content> contents;
    private Stack<Content> stack;

    public Builder() {
        contents = new ArrayList<>();
        stack = new Stack<>();
    }

    public void buildText(String string) {
        if (stack.empty()) {
            contents.add(new Text(string));
        } else {
            Iterator<Content> it = stack.peek().iterator();
            while (it.hasNext()) {
                it.next();
            }
            stack.peek().insert(new Text(string), it);
        }
    }

    public List<Content> getContents() {
        return contents;
    }

    public void beginHybrid() {
        HybridContent hybrid = new HybridContent(new ArrayList<>());
        stack.push(hybrid);
    }

    public void endHybrid() {
        HybridContent hybrid = (HybridContent)stack.pop();
        if (stack.empty()) {
            contents.add(hybrid);
        } else {
            Iterator<Content> it = stack.peek().iterator();
            while (it.hasNext()) {
                it.next();
            }
            stack.peek().insert(hybrid, it);
        }
    }
}
