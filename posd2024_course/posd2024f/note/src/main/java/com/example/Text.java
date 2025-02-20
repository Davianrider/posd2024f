package com.example;

public class Text implements Content {
    private final String text;
    private int currentPosition;

    public Text(String text) {
        this.currentPosition = 0;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visitText(this);
    }

    public int getPosition() {
        return currentPosition;
    }

    public void setPosition(int position) {
        this.currentPosition = position;
    }
}
