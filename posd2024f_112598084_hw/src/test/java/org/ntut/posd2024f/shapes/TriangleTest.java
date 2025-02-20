package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TriangleTest {
    @Test
    public void TriangleTestInit(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(1, 1));
        vertices.add(new TwoDimensionalVector(2, 2));
        Exception exception = assertThrows(Exception.class, () ->{
            new Triangle(vertices);
        });
        assertEquals("It's not a triangle!", exception.getMessage());
    }
    
    @Test
    public void TriangleTestPerimeter(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Triangle triangle = new Triangle(vertices);
        assertEquals(2, triangle.area(), 0.001);
    }

    @Test
    public void TriangleTestArea(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Triangle triangle = new Triangle(vertices);
        assertEquals(4 + 2 * Math.sqrt(2), triangle.perimeter(), 0.001);
    }

    @Test
    public void triangleGetVectorsTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Triangle triangle = new Triangle(vertices);
        assertEquals(vertices, triangle.getVectors());
    }

}