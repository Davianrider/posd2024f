package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundShape implements Shape {
    private List<Shape> shapes;
    
    public List<Shape> getShapes(){
        return shapes;
    }
    
    public CompoundShape() {
        this.shapes = new ArrayList<>();
    }
    
    @Override
    public double area() {
        double shapeArea = 0;
        int k = shapes.size();
        for (int a = 0; a < k; a++)
            shapeArea += shapes.get(a).area();

        return shapeArea;
    }

    @Override
    public double perimeter() {
        double shapePerimeter = 0;
        int k = shapes.size();
        for (int a = 0; a < k; a++)
            shapePerimeter += shapes.get(a).perimeter();
        
        return shapePerimeter;
    }

    @Override
    public void add(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitCompoundShape(this);
    }

}