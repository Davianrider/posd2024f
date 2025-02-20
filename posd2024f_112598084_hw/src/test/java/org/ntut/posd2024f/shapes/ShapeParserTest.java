package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class ShapeParserTest {

    @Test
    public void testParseCircle() throws Exception {
        File testFile = new File("test_circle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Circle 3.0, color=RED");
        }

        ShapeParser parser = new ShapeParser(testFile);  
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ColoredShape.class, shapes.get(0).getClass());

        testFile.delete();
    }

    @Test
    public void testParseRectangle() throws Exception {
        File testFile = new File("test_rectangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Rectangle 4 6");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(Rectangle.class, shapes.get(0).getClass());

        Rectangle rectangle = (Rectangle) shapes.get(0);
        assertEquals(4, rectangle.getLength(), 0.01);
        assertEquals(6, rectangle.getWidth(), 0.01);

        testFile.delete();
    }
    
    @Test
    public void testParseTriangle() throws Exception {
        File testFile = new File("test_triangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle [4,0] [4,3] [0,3]");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(Triangle.class, shapes.get(0).getClass());

        Triangle triangle = (Triangle) shapes.get(0);
        List<TwoDimensionalVector> vectors = triangle.getVectors();

        assertEquals(3, vectors.size());
        assertEquals("[4,0]", vectors.get(0).toString());
        assertEquals("[4,3]", vectors.get(1).toString());
        assertEquals("[0,3]", vectors.get(2).toString());

        testFile.delete();
    }

    @Test
    public void testParseConvexPolygon() throws Exception {
        File testFile = new File("test_convex_polygon.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("ConvexPolygon [4,0] [4,3] [0,3] [0,0]");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ConvexPolygon.class, shapes.get(0).getClass()); 

        ConvexPolygon convexPolygon = (ConvexPolygon) shapes.get(0);
        List<TwoDimensionalVector> vectors = convexPolygon.getVectors();

        assertEquals(4, vectors.size());
        assertEquals("[4,0]", vectors.get(0).toString());
        assertEquals("[4,3]", vectors.get(1).toString());
        assertEquals("[0,3]", vectors.get(2).toString());
        assertEquals("[0,0]", vectors.get(3).toString());

        testFile.delete();
    }

    @Test
    public void testParseCompoundShape() throws Exception {
        File testFile = new File("test_compound_shape.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape {");
            writer.println("    Triangle [4,0] [4,3] [0,3]");
            writer.println("    Rectangle 4 6");
            writer.println("}");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(CompoundShape.class, shapes.get(0).getClass());
        CompoundShape compoundShape = (CompoundShape) shapes.get(0);
        assertEquals(2, compoundShape.getShapes().size()); 
        assertEquals(Triangle.class, compoundShape.getShapes().get(0).getClass());
        assertEquals(Rectangle.class, compoundShape.getShapes().get(1).getClass());

        testFile.delete();
    }
    
    @Test
    public void testNonExistFileThrowException() throws Exception {
        File testFile = new File("test_non_exist_file.txt");
        if (testFile.exists()) {
            testFile.delete();
        }

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ShapeParser(testFile);
        });
        assertEquals("File not found", exception.getMessage());
    }

    @Test
    public void testCompoundShapeEmpty() throws Exception {
        File testFile = new File("test_compound_shape_missing_left_brace.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape {}");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        assertEquals(1, parser.getResult().size());
        assertEquals(CompoundShape.class, parser.getResult().get(0).getClass());
        CompoundShape compoundShape = (CompoundShape) parser.getResult().get(0);
        assertFalse(compoundShape.iterator().hasNext());
        testFile.delete();
    }
    
    @Test
    public void testCompoundShapeMissingLeftBraceThrowException() throws Exception {
        File testFile = new File("test_compound_shape_missing_left_brace.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape ");
            writer.println("}");
        }

        ShapeParser parser = new ShapeParser(testFile);

        Exception exception = assertThrows(IllegalArgumentException.class, parser::parse);
        assertEquals("Expected token '{'", exception.getMessage());

        testFile.delete();
    }

    @Test
    public void testCompoundShapeMissingRightBraceThrowException() throws Exception {
        File testFile = new File("test_compound_shape_missing_right_brace.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape {");
            writer.println("CompoundShape {");
            writer.println("Rectangle 3.0 4.0, color=GREEN");
        }

        ShapeParser parser = new ShapeParser(testFile);

        Exception exception = assertThrows(IllegalArgumentException.class, parser::parse);
        assertEquals("Expected token '}'", exception.getMessage());

        testFile.delete();
    }

    @Test
    public void testVectorMissingCommaThrowException() throws Exception {
        File testFile = new File("test_vector_missing_comma.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle [40] [43] []");
        }

        ShapeParser parser = new ShapeParser(testFile);

        Exception exception = assertThrows(IllegalArgumentException.class, parser::parse);
        assertEquals("Expected token ','", exception.getMessage());

        testFile.delete();
    }

    @Test
    public void testVectorMissingLeftSquareBracketThrowException() throws Exception {
        File testFile = new File("test_vector_missing_left_square_bracket.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle 4,0] [4,3] [0,3]");
        }

        ShapeParser parser = new ShapeParser(testFile);

        Exception exception = assertThrows(IllegalArgumentException.class, parser::parse);
        assertEquals("Expected token '['", exception.getMessage());

        testFile.delete();
    }

    @Test
    public void testVectorMissingRightSquareBracketThrowException() throws Exception {
        File testFile = new File("test_vector_missing_right_square_bracket.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle [4,0 [4,3] [0,3]");
        }

        ShapeParser parser = new ShapeParser(testFile);

        Exception exception = assertThrows(IllegalArgumentException.class, parser::parse);
        assertEquals("Expected token ']'", exception.getMessage());

        testFile.delete();
    }

    @Test
    public void testParseColoredCircle() throws Exception {
        File testFile = new File("test_colored_circle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Circle 3.0, color=RED");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ColoredShape.class, shapes.get(0).getClass());

        ColoredShape coloredShape = (ColoredShape) shapes.get(0);
        assertEquals(Circle.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredRectangle() throws Exception {
        File testFile = new File("test_colored_rectangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Rectangle 3.0 4.0, color=RED");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ColoredShape.class, shapes.get(0).getClass());

        ColoredShape coloredShape = (ColoredShape) shapes.get(0);
        assertEquals(Rectangle.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredTriangle() throws Exception {
        File testFile = new File("test_colored_triangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle [4,0] [4,3] [0,3], color=RED");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ColoredShape.class, shapes.get(0).getClass());

        ColoredShape coloredShape = (ColoredShape) shapes.get(0);
        assertEquals(Triangle.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredConvexPolygon() throws Exception {
        File testFile = new File("test_colored_convex_polygon.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("ConvexPolygon [4,0] [4,3] [0,3] [0,0], color=RED");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ColoredShape.class, shapes.get(0).getClass());

        ColoredShape coloredShape = (ColoredShape) shapes.get(0);
        assertEquals(ConvexPolygon.class, coloredShape.getShape().getClass()); 
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }
    
    @Test
    public void testParseColoredCompoundShape() throws Exception {
        File testFile = new File("test_colored_compound_shape.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape, color=RED {");
            writer.println("}");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(ColoredShape.class, shapes.get(0).getClass());

        ColoredShape coloredShape = (ColoredShape) shapes.get(0);
        assertEquals(CompoundShape.class, coloredShape.getShape().getClass());    
        
        testFile.delete();
    }

    @Test
    public void testParseTextedCircle() throws Exception {
        File testFile = new File("test_texted_circle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Circle 3.0, text=This is a circle");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(Circle.class, textedShape.getShape().getClass());
        assertEquals("This is a circle", textedShape.getText());

        testFile.delete();
    }

    @Test
    public void testParseTextedRectangle() throws Exception {
        File testFile = new File("test_texted_rectangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Rectangle 3.0 4.0, text=This is a rectangle");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(Rectangle.class, textedShape.getShape().getClass());
        assertEquals("This is a rectangle", textedShape.getText());

        testFile.delete();
    }

    @Test
    public void testParseTextedTriangle() throws Exception {
        File testFile = new File("test_texted_triangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle [4,0] [4,3] [0,3], text=This is a triangle");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(Triangle.class, textedShape.getShape().getClass());
        assertEquals("This is a triangle", textedShape.getText());

        testFile.delete();
    }

    @Test
    public void  testParseTextedConvexPolygon() throws Exception {
        File testFile = new File("test_texted_convex_polygon.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("ConvexPolygon [4,0] [4,3] [0,3] [0,0], text=This is a convex polygon");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(ConvexPolygon.class, textedShape.getShape().getClass());
        testFile.delete();
    }

    @Test
    public void testParseTextedCompoundShape() throws Exception {
        File testFile = new File("test_texted_compound_shape.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape, text=This is a compound shape {");
            writer.println("}");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(CompoundShape.class, textedShape.getShape().getClass());

        testFile.delete();
    }

    @Test
    public void testParseComplexShape() throws Exception {
        File testFile = new File("test_complex_shape.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape, text=This is a complex compound shape {");
            writer.println("    CompoundShape, color=RED {");
            writer.println("        ConvexPolygon [4,0] [4,3] [0,3] [0,0], text=This is a convex polygon");
            writer.println("        Circle 3.0");
            writer.println("        CompoundShape {");
            writer.println("            Circle 3.0, color=GREEN");
            writer.println("        }");
            writer.println("    }");
            writer.println("    Rectangle 3.0 4.0");
            writer.println("    Triangle [4,0] [4,3] [0,3], text=This is a triangle");
            writer.println("    CompoundShape {}");
            writer.println("}");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        Iterator<Shape> it1 = shapes.get(0).iterator();
        Shape compoundShape1 = it1.next();
        Iterator<Shape> it2 = compoundShape1.iterator();
        assertEquals(ColoredShape.class, compoundShape1.getClass());
        assertEquals(TextedShape.class, it2.next().getClass());
        assertEquals(Circle.class, it2.next().getClass());

        Shape compoundShape2 = it2.next();
        assertEquals(CompoundShape.class, compoundShape2.getClass());
        
        Iterator<Shape> it3 = compoundShape2.iterator();
        assertEquals(ColoredShape.class, it3.next().getClass());

        
        assertEquals(Rectangle.class, it1.next().getClass());
        assertEquals(TextedShape.class, it1.next().getClass());     
        
        Shape compoundShape3 = it1.next();
        Iterator<Shape> it4 = compoundShape3.iterator();
        assertEquals(CompoundShape.class, compoundShape3.getClass());
        
        assertFalse(it1.hasNext());
        assertFalse(it2.hasNext());
        assertFalse(it3.hasNext());
        assertFalse(it4.hasNext());
        
        testFile.delete();
    }

    @Test
    public void testParseColoredTextedCircle() throws Exception {
        File testFile = new File("test_colored_texted_circle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Circle 3.0, color=RED, text=This is a circle");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(ColoredShape.class, textedShape.getShape().getClass());
        assertEquals("This is a circle", textedShape.getText());

        ColoredShape coloredShape = (ColoredShape) textedShape.getShape();
        assertEquals(Circle.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredTextedRectangle() throws Exception {
        File testFile = new File("test_colored_texted_rectangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Rectangle 3.0 4.0, color=RED, text=This is a rectangle");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(ColoredShape.class, textedShape.getShape().getClass());
        assertEquals("This is a rectangle", textedShape.getText());

        ColoredShape coloredShape = (ColoredShape) textedShape.getShape();
        assertEquals(Rectangle.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredTextedTriangle() throws Exception {
        File testFile = new File("test_colored_texted_triangle.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("Triangle [4,0] [4,3] [0,3], color=RED, text=This is a triangle");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(ColoredShape.class, textedShape.getShape().getClass());
        assertEquals("This is a triangle", textedShape.getText());

        ColoredShape coloredShape = (ColoredShape) textedShape.getShape();
        assertEquals(Triangle.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredTextedConvexPolygon() throws Exception {
        File testFile = new File("test_colored_texted_convex_polygon.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("ConvexPolygon [4,0] [4,3] [0,3] [0,0], color=RED, text=This is a convex polygon");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(ColoredShape.class, textedShape.getShape().getClass());
        assertEquals("This is a convex polygon", textedShape.getText());

        ColoredShape coloredShape = (ColoredShape) textedShape.getShape();
        assertEquals(ConvexPolygon.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

    @Test
    public void testParseColoredTextedCompoundShape() throws Exception {
        File testFile = new File("test_colored_texted_compound_shape.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            writer.println("CompoundShape, color=RED, text=This is a compound shape {");
            writer.println("}");
        }

        ShapeParser parser = new ShapeParser(testFile);
        parser.parse();
        
        List<Shape> shapes = parser.getResult();

        assertEquals(1, shapes.size());
        assertEquals(TextedShape.class, shapes.get(0).getClass());

        TextedShape textedShape = (TextedShape) shapes.get(0);
        assertEquals(ColoredShape.class, textedShape.getShape().getClass());
        assertEquals("This is a compound shape", textedShape.getText());

        ColoredShape coloredShape = (ColoredShape) textedShape.getShape();
        assertEquals(CompoundShape.class, coloredShape.getShape().getClass());
        assertEquals("RED", coloredShape.getColor());

        testFile.delete();
    }

}