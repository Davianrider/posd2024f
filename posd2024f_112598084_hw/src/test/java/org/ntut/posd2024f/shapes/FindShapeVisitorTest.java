package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FindShapeVisitorTest {
    @Test
    public void findShapeVisitorVisitCircleTest(){
        List<Shape> shapes = new ArrayList<Shape>(); 
        Shape circle = new Circle(2);
        shapes.add(circle);

        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        circle.accept(visitor);
        
        assertEquals(shapes, visitor.getResult());
    }

    @Test 
    public void findShapesVisitorVisitRectangle(){
        List<Shape> shapes = new ArrayList<>();
        Shape rectangle = new Rectangle(3, 4);
        shapes.add(rectangle);

        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        rectangle.accept(visitor);

        assertEquals(shapes, visitor.getResult());
    }

    @Test
    public void findShapesVisitorVisitTriangleTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Triangle triangle = new Triangle(vertices);       
        
        List<Shape> shapes = new ArrayList<>();
        shapes.add(triangle);

        Predicate<Shape> condition = shape -> shape.area() > 1;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        triangle.accept(visitor);

        assertEquals(shapes, visitor.getResult());
    }

    @Test
    public void  findShapesVisitorVisitConvexPolygon(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        ConvexPolygon convexPolygon = new ConvexPolygon(vertices);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(convexPolygon);

        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        convexPolygon.accept(visitor);

        assertEquals(shapes, visitor.getResult());
    }

    @Test
    public void findShapesVisitorVisitCompoundShapeTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        List<Shape> list = new ArrayList<>();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        
        Shape compoundShape = new CompoundShape();
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);
        
        list.add(compoundShape);
        list.add(circle);
        list.add(rectangle);
        list.add(convexPoly);

        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        compoundShape.accept(visitor);

        assertEquals(list, visitor.getResult());
    }

    @Test
    public void findShapesVisitorVisitTextedShapeTest(){
        Shape circle = new Circle(5);
        Shape textedShape = new TextedShape(circle, "CIRCLE");
        List<Shape> shapes = new ArrayList<>();

        shapes.add(textedShape);
        shapes.add(circle);

        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        textedShape.accept(visitor);

        assertEquals(shapes, visitor.getResult());
    }

    @Test
    public void findShapesVisitorVisitColoredShapeTest(){
        Shape circle = new Circle(5);
        Shape colorShape = new ColoredShape(circle, "BLUE");
        List<Shape> shapes = new ArrayList<>();

        shapes.add(colorShape);
        shapes.add(circle);

        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        colorShape.accept(visitor);

        assertEquals(shapes, visitor.getResult());
    }

    @Test
    public void findShapesVisitorGetResultTest(){
        List<Shape> shapes = new ArrayList<>();
        Predicate<Shape> condition = shape -> shape.area() > 2;
        FindShapeVisitor visitor = new FindShapeVisitor(condition);
        assertEquals(shapes, visitor.getResult());
    }

}

