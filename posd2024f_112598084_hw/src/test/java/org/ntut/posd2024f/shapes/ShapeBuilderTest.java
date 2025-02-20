package org.ntut.posd2024f.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ShapeBuilderTest {

    @Test
    public void testBuildCircleShape() {
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildCircle(2.1, null, null);
        assertEquals(Circle.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildColoredCircleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildCircle(2.1, "RED", null);
        assertEquals(ColoredShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildTextedShapeCircleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildCircle(2.1, null, "Circle");
        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildColoredTextedShapeCircleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildCircle(2.1, "RED", "Circle");
        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildRectangleShape() {
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildRectangle(1, 2, null, null);
        assertEquals(Rectangle.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildColoredRectangleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildRectangle(1, 2, "RED", null);
        assertEquals(ColoredShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildTextedShapeRectangleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildRectangle(1, 2, null, "Rectangle");
        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildColoredTextedShapeRectangleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        builder.buildRectangle(1, 2, "RED", "Rectangle");
        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildTriangleShape() {
        ShapeBuilder builder = new ShapeBuilder();
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        builder.buildTriangle(vectors, null, null);
        assertEquals(Triangle.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildColoredTriangleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        builder.buildTriangle(vectors, "RED", null);
        assertEquals(ColoredShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildTextedShapeTriangleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        builder.buildTriangle(vectors, null, "Triangle");
        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildColoredTextedShapeTriangleShape(){
        ShapeBuilder builder = new ShapeBuilder();
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        builder.buildTriangle(vectors, "RED", "Triangle");
        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
    }

    @Test
    public void testBuildCompoundShape(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        
        ShapeBuilder builder = new ShapeBuilder();
        builder.beginBuildCompoundShape(null, null);
        builder.buildCircle(2.1, null, null);
        builder.buildRectangle(1, 2, null, null);
        builder.buildTriangle(vectors, null, null);
        builder.endBuildCompoundShape();

        Iterator<Shape> iterator = builder.getResult().get(0).iterator();

        assertEquals(CompoundShape.class, builder.getResult().get(0).getClass());
        assertEquals(Circle.class, iterator.next().getClass());
        assertEquals(Rectangle.class, iterator.next().getClass());
        assertEquals(Triangle.class, iterator.next().getClass());
    }

    @Test
    public void testBuildColoredCompoundShape(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        
        ShapeBuilder builder = new ShapeBuilder();
        builder.beginBuildCompoundShape("RED", null);
        builder.buildCircle(2.1, null, null);
        builder.buildRectangle(1, 2, null, null);
        builder.buildTriangle(vectors, null, null);
        builder.endBuildCompoundShape();

        Iterator<Shape> iterator = builder.getResult().get(0).iterator();

        assertEquals(ColoredShape.class, builder.getResult().get(0).getClass());
        assertEquals(Circle.class, iterator.next().getClass());
        assertEquals(Rectangle.class, iterator.next().getClass());
        assertEquals(Triangle.class, iterator.next().getClass());
    }

    @Test
    public void testBuildTextedCompoundShape(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        
        ShapeBuilder builder = new ShapeBuilder();
        builder.beginBuildCompoundShape(null, "CompoundShape");
        builder.buildCircle(2.1, null, null);
        builder.buildRectangle(1, 2, null, null);
        builder.buildTriangle(vectors, null, null);
        builder.endBuildCompoundShape();

        Iterator<Shape> iterator = builder.getResult().get(0).iterator();

        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
        assertEquals(Circle.class, iterator.next().getClass());
        assertEquals(Rectangle.class, iterator.next().getClass());
        assertEquals(Triangle.class, iterator.next().getClass());
    }

    @Test
    public void testBuildColoredTextedCompoundShape(){
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        
        ShapeBuilder builder = new ShapeBuilder();
        builder.beginBuildCompoundShape("RED", "CompoundShape");
        builder.buildCircle(2.1, null, null);
        builder.buildRectangle(1, 2, null, null);
        builder.buildTriangle(vectors, null, null);
        builder.endBuildCompoundShape();

        Iterator<Shape> iterator = builder.getResult().get(0).iterator();

        assertEquals(TextedShape.class, builder.getResult().get(0).getClass());
        assertEquals(Circle.class, iterator.next().getClass());
        assertEquals(Rectangle.class, iterator.next().getClass());
        assertEquals(Triangle.class, iterator.next().getClass());
    }

    @Test
    public void testBuildComplexShape() {
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        vectors.add(new TwoDimensionalVector(0, 0));
        vectors.add(new TwoDimensionalVector(2, 2));
        vectors.add(new TwoDimensionalVector(0, 2));
        
        ShapeBuilder builder = new ShapeBuilder();
        builder.beginBuildCompoundShape("RED", null);
            builder.beginBuildCompoundShape(null, null);
                builder.buildCircle(2.1, null, null);
            builder.endBuildCompoundShape();
            builder.buildRectangle(1, 2, null, null);
            builder.buildTriangle(vectors, null, null);
        builder.endBuildCompoundShape();
    
        Shape outerShape = builder.getResult().get(0);
        assertEquals(ColoredShape.class, outerShape.getClass());
        
        Iterator<Shape> iterator1 = outerShape.iterator();
        
        Shape shape1 = iterator1.next();
        assertEquals(CompoundShape.class, shape1.getClass());
        
        Iterator<Shape> iterator2 = ((CompoundShape) shape1).iterator();
        Shape shape2 = iterator2.next();
        assertEquals(Circle.class, shape2.getClass());
    
        Shape shape3 = iterator1.next();
        Shape shape4 = iterator1.next();
        assertEquals(Rectangle.class, shape3.getClass());
        assertEquals(Triangle.class, shape4.getClass());
    }
}
