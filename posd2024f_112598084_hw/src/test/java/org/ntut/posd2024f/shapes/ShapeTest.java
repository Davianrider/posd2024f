package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ShapeTest {
    @Test
    public void ShapeCircleTest() throws Exception{
        double radius = 2;
        Shape circle = new Circle(radius);
        assertEquals(circle.area(), radius * radius * Math.PI, 0.001);
        assertEquals(circle.perimeter(), 2 * radius * Math.PI, 0.001);
    }

    @Test
    public void ShapeRectangleTest() throws Exception{
        double length = 2;
        double width = 3;
        Shape rectangle = new Rectangle(length, width);
        assertEquals(rectangle.area(), length * width, 0);
        assertEquals(rectangle.perimeter(), 2 * (length + width), 0);
        }

    @Test
    public void ShapeTriangleTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Shape triangle = new Triangle(vertices);
        assertEquals(2, triangle.area(), 0.001);
        assertEquals(4 + 2 * Math.sqrt(2) , triangle.perimeter(), 0);
    }

    @Test
    public void convexPolyTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Shape convexPoly = new ConvexPolygon(vertices);
        assertEquals(4, convexPoly.area(), 0.001);
        assertEquals(8, convexPoly.perimeter(), 0.001);
    }

    @Test
    public void addTest() throws Exception {
        Shape circle = new Circle(2);
        Exception exception = assertThrows(Exception.class, () -> {
            circle.add(new Rectangle(3, 4));
        });
        assertEquals("Illegal Operation", exception.getMessage());
    }

    @Test
    public void iteratorTest() throws Exception{
        Shape circle = new Circle(2);
        Iterator<Shape> iterator = circle.iterator();
        assertFalse(iterator.hasNext());
        Exception exception = assertThrows(Exception.class, () ->{
            iterator.next();
        });
       assertEquals("Null iterator does not point to any element", exception.getMessage());
    }
    
}