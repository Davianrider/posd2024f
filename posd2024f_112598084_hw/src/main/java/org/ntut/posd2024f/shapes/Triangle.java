package org.ntut.posd2024f.shapes;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Triangle implements Shape
{
    private TwoDimensionalVector aVector;
    private TwoDimensionalVector bVector;
    private TwoDimensionalVector cVector;
    
    private double a;
    private double b;
    private double c;
    private double s;
    
    public Triangle(List<TwoDimensionalVector> vectors) throws ShapeException {
        aVector = vectors.get(0);
        bVector = vectors.get(1);
        cVector = vectors.get(2);
        
        TwoDimensionalVector abVector = aVector.subtract(bVector);
        TwoDimensionalVector bcVector = bVector.subtract(cVector);
        TwoDimensionalVector acVector = aVector.subtract(cVector);
        if(abVector.length() <= 0 || bcVector.length() <= 0 || acVector.length() <= 0 || (abVector.length() + bcVector.length()) <= acVector.length() || (abVector.length() + acVector.length()) <= bcVector.length() || (bcVector.length() + acVector.length()) <= acVector.length())
            throw new ShapeException("It's not a triangle!");
        
        this.a = abVector.length();
        this.b = bcVector.length();
        this.c = acVector.length();
        s = (abVector.length() + bcVector.length() + acVector.length()) / 2;
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public double area() {
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitTriangle(this);
    }

    public List<TwoDimensionalVector> getVectors() {
        List<TwoDimensionalVector> allVectors = new ArrayList<TwoDimensionalVector>();
        allVectors.add(aVector);
        allVectors.add(bVector);
        allVectors.add(cVector);
        return allVectors;
    }

}
