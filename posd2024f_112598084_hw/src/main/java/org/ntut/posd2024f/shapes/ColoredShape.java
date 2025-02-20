package org.ntut.posd2024f.shapes;

import java.util.Iterator;

public class ColoredShape implements Shape {

    private Shape shapes;
    private String colored;

    public ColoredShape(Shape shape, String color) throws ShapeException {
        if (!"red".equals(color) && !"RED".equals(color) && !"GREEN".equals(color) && !"BLUE".equals(color)) {
            throw new ShapeException("Invalid color! Color must be RED, GREEN, or BLUE.");
        }
        this.shapes = shape;
        colored = color;
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
        shapes.add(shape);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public Shape getShape() {
        return shapes;
    }

    public String getColor() {
        return colored;
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitColoredShape(this);
    }

}