package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class CircleTest {
    private double radius = 1.1;
    
    @Test
    public void testCircleInitializationWithNegativeRadius() {
        Exception exception = assertThrows(ShapeException.class, () -> {
            new Circle(-2.1);
        });
        assertEquals("It's not a circle!", exception.getMessage());
    }

    @Test
    public void testCircleInitializationWithZeroRadius() {
        Exception exception = assertThrows(ShapeException.class, () -> {
            new Circle(0);
        });
        assertEquals("It's not a circle!", exception.getMessage());
    }

    @Test
    public void CircleTestArea() throws Exception{
        Circle areaCircle = new Circle(radius);
        assertEquals(areaCircle.area(), Math.PI * radius * radius, 0.001);
    }

    @Test
    public void CircleTestPerimeter() throws Exception{
        Circle perimeterCircle = new Circle(radius);
        assertEquals(perimeterCircle.perimeter(), Math.PI * 2 *radius, 0.001);
    }

    @Test
    public void circleGetRadiusTesr() throws Exception{
        Circle getRadiusCircle = new Circle(radius);
        assertEquals(radius, getRadiusCircle.getRadius());
    }

}