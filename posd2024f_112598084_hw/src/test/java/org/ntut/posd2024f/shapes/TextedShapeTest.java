package org.ntut.posd2024f.shapes;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TextedShapeTest {
    @Test
    public void textShapeAreaTest(){
        Shape circle = new Circle(2.1);
        Shape textedShape = new TextedShape(circle, "CIRCLE");
        assertEquals(2.1 * 2.1 * Math.PI, textedShape.area());
    }

    @Test
    public void textShapePerimeterTest(){
        Shape circle = new Circle(2.1);
        Shape textedShape = new TextedShape(circle, "CIRCLE");
        assertEquals(2. * 2.1 * Math.PI, textedShape.perimeter());
    }

    @Test
    public void textedShapeAddTest(){
        Shape circle = new Circle(2.1);
        Shape rectangle = new Rectangle(2, 3);
        Shape textedShape = new TextedShape(circle, "CIRCLE");
        Exception exception = assertThrows(ShapeException.class, () -> {
            textedShape.add(rectangle);
        });
        assertEquals("Illegal Operation", exception.getMessage());
    }

    @Test
    public void textedShapeIteratorTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);

        Shape textedShape = new TextedShape(compoundShape, "COMPOUNDSHAPE");
        Iterator<Shape> iterator = textedShape.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(circle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(rectangle, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(convexPoly, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void textedShapeGetShapeTest(){
        Shape circle = new Circle(2.1);
        TextedShape textedShape = new TextedShape(circle, "CIRCLE");
        assertEquals(circle, textedShape.getShape());
    }

    @Test
    public void textedShapeGetTextTest(){
        Shape circle = new Circle(2.1);
        TextedShape textedShape = new TextedShape(circle, "CIRCLE");
        assertEquals("CIRCLE", textedShape.getText());
    }
    
}