package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class TwoDimensionalVectorTest {

    @Test
    public void twoDimensionalVectorGetXTest(){
        TwoDimensionalVector vector = new TwoDimensionalVector(3, 4);
        assertEquals(3, vector.getX());
    }

    @Test
    public void twoDimensionalVectorGetYTest(){
        TwoDimensionalVector vector = new TwoDimensionalVector(3, 4);
        assertEquals(4, vector.getY());
    }

    @Test
    public void lengthTest() throws Exception{
        TwoDimensionalVector vector = new TwoDimensionalVector(3, 4);
        assertEquals(Math.sqrt((3 * 3) + (4 * 4)), vector.length() , 0.001);
    }

    @Test
    public void dotTest() throws Exception{
        TwoDimensionalVector vector1 = new TwoDimensionalVector(3, 4);
        TwoDimensionalVector vector2 = new TwoDimensionalVector(8, 9);
        assertEquals((3 * 8) + (4 * 9), vector1.dot(vector2), 0.001);
    }

    @Test
    public void crossTest() throws Exception{
        TwoDimensionalVector vector1 = new TwoDimensionalVector(3, 4);
        TwoDimensionalVector vector2 = new TwoDimensionalVector(8, 9);
        assertEquals((3 * 9) - (8 * 4), vector1.cross(vector2), 0.001);
    }

    @Test
    public void subtractTest(){
        TwoDimensionalVector vector1 = new TwoDimensionalVector(3, 4);
        TwoDimensionalVector vector2 = new TwoDimensionalVector(8, 9);
        TwoDimensionalVector subVector = vector1.subtract(vector2);
        assertEquals(3 - 8, subVector.getX(), 0.001);
        assertEquals(4 - 9, subVector.getY(), 0.001);
    }

    @Test
    public void twoDimensionalVectorToStringTest(){
        TwoDimensionalVector vector = new TwoDimensionalVector(3, 4);
        assertEquals("[3,4]", vector.toString());
    }
}
