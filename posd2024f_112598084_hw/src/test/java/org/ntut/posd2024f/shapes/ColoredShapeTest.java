package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class ColoredShapeTest {

    @Test
    public void coloredShapeTest(){
        Shape shape = new Circle(2.1);
        String color = "ORANDE";
        Exception exception = assertThrows(ShapeException.class, () -> {
            new ColoredShape(shape, color);
        });
        assertEquals("Invalid color! Color must be RED, GREEN, or BLUE.", exception.getMessage());
    }

    @Test
    public void coloredShapeAreaTest(){
        Shape shape = new Circle(2.1);
        String color = "RED";
        ColoredShape coloredShape = new ColoredShape(shape, color);
        assertEquals(2.1 * 2.1 * Math.PI, coloredShape.area());
    }

    @Test
    public void coloredShapePerimeterTest(){
        Shape shape = new Circle(2.1);
        String color = "RED";
        ColoredShape coloredShape = new ColoredShape(shape, color);
        assertEquals(2 * 2.1 * Math.PI, coloredShape.perimeter());
    }

    @Test
    public void coloredShapeAddTest(){
        Shape shape = new Circle(2);
        String color = "RED";
        ColoredShape coloredShape = new ColoredShape(shape, color);
        
        Exception exception = assertThrows(ShapeException.class, () -> {
            coloredShape.add(shape);
         });
        assertEquals("Illegal Operation", exception.getMessage());       
    }

    @Test
    public void testColoredShapeIteratorWithCompoundShape() {

        CompoundShape compoundShape = new CompoundShape();
        Circle circle = new Circle(2.0);
        Rectangle rectangle = new Rectangle(3.0, 4.0);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        ColoredShape coloredShape = new ColoredShape(compoundShape, "RED");

        Iterator<Shape> iterator = coloredShape.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(circle, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(rectangle, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testColoredShapeIteratorWithSingleShape() {

        Circle circle = new Circle(2.0);
        ColoredShape coloredShape = new ColoredShape(circle, "GREEN");
        Iterator<Shape> iterator = coloredShape.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void coloredShapeGetShapeTest(){
        Circle circle = new Circle(2.0);
        ColoredShape coloredShape = new ColoredShape(circle, "GREEN");
        assertEquals(circle, coloredShape.getShape());
    }
    
    @Test
    public void coloredShapeGetColorTest(){
        Circle circle = new Circle(2.0);
        ColoredShape coloredShape = new ColoredShape(circle, "GREEN");
        assertEquals("GREEN", coloredShape.getColor());
    } 
}