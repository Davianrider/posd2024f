package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompoundShapeTest {

    @Test
    public void compoundShapeTest() throws Exception{
        
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);
        
        assertEquals(circle, compoundShape.getShapes().get(0));
        assertEquals(rectangle, compoundShape.getShapes().get(1));
        assertEquals(convexPoly, compoundShape.getShapes().get(2));
    }

    @Test
    public void areaTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);
        double totalArea = circle.area() + rectangle.area() + convexPoly.area();
        
        assertEquals(totalArea, compoundShape.area(), 0.001);
    }

    @Test
    public void perimeterTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);
        double totalPerimeter = circle.perimeter() + rectangle.perimeter() + convexPoly.perimeter();

        assertEquals(totalPerimeter, compoundShape.perimeter(), 0.001);
    }

    @Test
    public void addTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);

        assertEquals(circle, compoundShape.getShapes().get(0));
        assertEquals(rectangle, compoundShape.getShapes().get(1));
        assertEquals(convexPoly, compoundShape.getShapes().get(2));
    }

    @Test
    public void iteratorTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);
        Iterator<Shape> iterator = compoundShape.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(circle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(rectangle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(convexPoly, iterator.next());
        assertFalse(iterator.hasNext());
    }
}