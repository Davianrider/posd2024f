package org.ntut.posd2024f.shapes;

public class TwoDimensionalVector {
    private int x;
    private int y;

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public TwoDimensionalVector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double length(){
        return Math.sqrt((x * x) + (y * y));
    }

    public int dot(TwoDimensionalVector vector){
        return (x * vector.getX()) + (y * vector.getY());

    } 

    public int cross(TwoDimensionalVector vector){
        return ((x * vector.getY()) - (vector.getX() * y));
    }

    public TwoDimensionalVector subtract(TwoDimensionalVector vector){
        int subX = x - vector.x;
        int subY = y - vector.y; 
        TwoDimensionalVector subVector = new TwoDimensionalVector(subX, subY);
        return subVector;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}