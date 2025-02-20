package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PrettyPrintVisitorTest {
    @Test
    public void prettyPrintVisitorVisitCircleTest(){
        Shape circle = new Circle(2.1);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        circle.accept(visitor);
        assertEquals("Circle 2.1", visitor.getResult());        
    }

    @Test
    public void prettyPrintVisitorVisitRectangleTest(){
        Shape rectangle = new Rectangle(1, 2);
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        rectangle.accept(visitor);
        assertEquals("Rectangle 1.0 2.0", visitor.getResult());        
    }

    @Test
    public void prettyPrintVisitorVisitTriangleTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Shape triangle = new Triangle(vertices);
        
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        triangle.accept(visitor);
        assertEquals("Triangle [0,0] [2,2] [0,2]", visitor.getResult());
    }

    @Test
    public void prettyPrintVisitorVisitConvexPolygonTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        Shape convexPolygon = new ConvexPolygon(vertices);
        
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        convexPolygon.accept(visitor);
        assertEquals("ConvexPolygon [0,0] [2,0] [2,2] [0,2]", visitor.getResult());
    }

    @Test
    public void prettyPrintVisitorVisitCompoundShapeTest(){
        List<TwoDimensionalVector> vertices = new ArrayList<>();
        vertices.add(new TwoDimensionalVector(0, 0));
        vertices.add(new TwoDimensionalVector(2, 0));
        vertices.add(new TwoDimensionalVector(2, 2));
        vertices.add(new TwoDimensionalVector(0, 2));
        
        CompoundShape compoundShape = new CompoundShape();
        Shape circle = new Circle(2);
        Shape rectangle = new Rectangle(5, 3);
        Shape  convexPoly = new ConvexPolygon(vertices);
        compoundShape.add(circle);
        compoundShape.add(rectangle);
        compoundShape.add(convexPoly);
        
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        compoundShape.accept(visitor);

        assertEquals("CompoundShape {\n"
                    + "  Circle 2.0\n"
                    + "  Rectangle 5.0 3.0\n"
                    + "  ConvexPolygon [0,0] [2,0] [2,2] [0,2]\n"
                    + "}", visitor.getResult());
    }

    @Test
    public void prettyPrintVisitorVisitTextedShapeTest(){
        Shape circle = new Circle(2.1);
        TextedShape textedShape = new TextedShape(circle, "Hello");

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        textedShape.accept(visitor);
        assertEquals("Circle 2.1, text: Hello", visitor.getResult());
    }

    @Test
    public void prettyPrintVisitorVisitColoredShapeTest(){
        Shape circle = new Circle(2.1);
        ColoredShape coloredShape = new ColoredShape(circle, "RED");

        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        coloredShape.accept(visitor);
        assertEquals("\033[0;31mCircle 2.1\033[0m", visitor.getResult());
    }

     @Test
    public void testPrettyPrintVistorTripleCompoundShape() {
        ArrayList<TwoDimensionalVector> vectors = new ArrayList<TwoDimensionalVector>();
        vectors.add(new TwoDimensionalVector(4, 0));
        vectors.add(new TwoDimensionalVector(4, 3));
        vectors.add(new TwoDimensionalVector(0, 3));
        CompoundShape compound = new CompoundShape();
        compound.add(new Triangle(vectors));
        CompoundShape comCompound = new CompoundShape();
        comCompound.add(compound);
        ColoredShape colorShape1 = new ColoredShape(new Rectangle(3.0, 4.0), "BLUE");
        TextedShape textShape = new TextedShape(colorShape1, "this is a rectangle with blue color");
        comCompound.add(textShape);
        ColoredShape colorShape2 = new ColoredShape(comCompound, "GREEN");
        CompoundShape comComCompound = new CompoundShape();
        comComCompound.add(new Circle (3.0));
        comComCompound.add(colorShape2);
        
        PrettyPrintVisitor vistor = new PrettyPrintVisitor();
        comComCompound.accept(vistor);

        assertEquals("CompoundShape {\n" + 
                    "  Circle 3.0\n" + 
                    "  \u001B[0;32mCompoundShape {\n" +
                    "    CompoundShape {\n" + 
                    "      Triangle [4,0] [4,3] [0,3]\n" + 
                    "    }\n" + 
                    "    \u001B[0;34mRectangle 3.0 4.0\u001B[0m, text: this is a rectangle with blue color\n" + 
                    "  }\u001B[0m\n" + 
                    "}", vistor.getResult());
    }

}
