package org.ntut.posd2024f.shapes;

import java.util.Iterator;

public class TextedShape implements Shape {
    private Shape shapes;
    private String text;
    
    public TextedShape(Shape shape, String text) {
        this.shapes = shape;
        this.text = text;
    }

    @Override
    public double area() {
        return shapes.area();
    }

    @Override
    public double perimeter() {
        return shapes.perimeter();
    }
    
    @Override
    public void add(Shape shape) {
        this.shapes.add(shape);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public Shape getShape() {
        return shapes;
    }

    public String getText() {
        return text;
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitTextedShape(this);
    }
}
