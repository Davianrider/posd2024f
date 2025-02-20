package org.ntut.posd2024f.shapes;

import java.util.List;

public class ConvexPolygon implements Shape{
    private List<TwoDimensionalVector> sortedVector;    
    
    public ConvexPolygon(List<TwoDimensionalVector> vectors) throws ShapeException{
        boolean convex = isConvex(vectors);
        
        if (vectors.size() <= 3 || !convex)
            throw new ShapeException("It's not a convex polygon!");
        
        this.sortedVector = vectors;
    }

    @Override
    public double area() {
        int n = sortedVector.size();
        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            TwoDimensionalVector current = sortedVector.get(i);
            TwoDimensionalVector next = sortedVector.get((i + 1) % n);
            sum += current.cross(next);
        }

        return Math.abs(sum) / 2.0;
    }

    @Override
    public double perimeter() {
        int n = sortedVector.size();
        double perimeter = 0.0;

        for (int i = 0; i < n; i++) {
            TwoDimensionalVector current = sortedVector.get(i);
            TwoDimensionalVector next = sortedVector.get((i + 1) % n);
            TwoDimensionalVector sub = current.subtract(next);

            double distance = Math.sqrt(Math.pow(sub.getX(), 2) + Math.pow(sub.getY(), 2));
            perimeter += distance;
        }

        return perimeter;
    }

    private boolean isConvex(List<TwoDimensionalVector> vectors){
        int k = vectors.size();
        int initsign = 0;
        
        for (int a = 0; a < k; a++ ){
            TwoDimensionalVector currentVertice = vectors.get(a);
            TwoDimensionalVector nextVertice = vectors.get((a + 1) % k);
            TwoDimensionalVector nextNexVertice = vectors.get((a + 2) % k);
            
            TwoDimensionalVector vector1 = nextVertice.subtract(currentVertice);
            TwoDimensionalVector vector2 = nextNexVertice.subtract(nextVertice);
            
            double crossProduct = vector1.cross(vector2);
            if (crossProduct != 0){
                int currentSign = (crossProduct > 0)? 1: -1;
                if (initsign == 0)
                    initsign = currentSign;
                else if (initsign != currentSign)
                    return false;
            }
        }
        return true;
    }

    @Override
    public <T> void accept(Visitor<T> visitor) {
        visitor.visitConvexPolygon(this);
    }

    public List<TwoDimensionalVector> getVectors() {
        return sortedVector;
    }

}