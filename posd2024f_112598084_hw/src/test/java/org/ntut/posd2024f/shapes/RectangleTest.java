package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class RectangleTest {
    private double length = 1.1;
    private double width = 3.3;
    
    @Test
    public void rectangleTestInit() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Rectangle(0, 0);
        });
        assertEquals("It's not a rectangle!", exception.getMessage());
    }

    @Test
    public void RectangleTestArea() {
        Rectangle areaRectangle = new Rectangle(length, width);
        assertEquals(areaRectangle.area(), length * width, 0);
    }

    @Test
    public void RectangleTestPerimeter() {
        Rectangle perimeterRectangle = new Rectangle(length, width);
        assertEquals(perimeterRectangle.area(), length * width, 0);
    }

    @Test
    public void rectangleGetLengthTest() {
        Rectangle rectangle = new Rectangle(2, 3);
        assertEquals(2, rectangle.getLength());
    }

    @Test
    public void rectangleGetWedithTest() {
        Rectangle rectangle = new Rectangle(2, 3);
        assertEquals(3, rectangle.getWidth());
    }

}