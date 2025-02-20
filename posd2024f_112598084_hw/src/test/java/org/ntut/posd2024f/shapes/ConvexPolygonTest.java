package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
public class ConvexPolygonTest {
    @Test
    public void convexPolygonTest() throws Exception{   
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(1, 1));
        vertices.add(new TwoDimensionalVector(0, 2));
        vertices.add(new TwoDimensionalVector(-5, 3));
        vertices.add(new TwoDimensionalVector(0, 1));
        Exception exception = assertThrows(Exception.class, () -> {
            new ConvexPolygon(vertices);
        });
        assertEquals("It's not a convex polygon!", exception.getMessage());
    }

    @Test 
    public void ConvexPolygonAreaTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        ConvexPolygon convexPolygon = new ConvexPolygon(vertices);
        assertEquals(2 * 2, convexPolygon.area(),0.001);
    }

    @Test
    public void ConvexPolygonPerimeterTest() throws Exception{
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        ConvexPolygon convexPolygon = new ConvexPolygon(vertices);
        assertEquals(4 * 2, convexPolygon.perimeter(),0.001);
    }

    @Test
    public void getVectorsTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        ConvexPolygon convexPolygon = new ConvexPolygon(vertices);
        assertEquals(vertices, convexPolygon.getVectors());
    }
}
