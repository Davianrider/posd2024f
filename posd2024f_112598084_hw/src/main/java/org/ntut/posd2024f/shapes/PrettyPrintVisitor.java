package org.ntut.posd2024f.shapes;

import java.util.List;

public class PrettyPrintVisitor implements Visitor<String>{
    private String result;
    private int indentLevel;

    public PrettyPrintVisitor() {
        this.result = new String();
        this.indentLevel = 0;
    }

    @Override
    public void visitCircle(Circle circle) {
        result = "Circle "+circle.getRadius();
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        result = "Rectangle " + rectangle.getLength() + " " + rectangle.getWidth();
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        List<TwoDimensionalVector> vectors = triangle.getVectors();
        result = "Triangle" + " " 
                 + vectors.get(0).toString() + " " 
                 + vectors.get(1).toString() + " " 
                 + vectors.get(2).toString();    
    }

    @Override
    public void visitConvexPolygon(ConvexPolygon convexPolygon) {
        List<TwoDimensionalVector> vectors = convexPolygon.getVectors();
        int size = vectors.size();
        result = "ConvexPolygon"+ " " ;
        for (int i = 0; i < size - 1; i++)
            result += vectors.get(i).toString() + " " ;
        if (size > 0)
            result += vectors.get(size - 1).toString();   
    }
    
    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        String indent = repeat("  ", indentLevel); 
        int size = compoundShape.getShapes().size();
        boolean hasInnerCompoundShape = false;

        if (size > 0) {
            result += indent + "CompoundShape {\n";
            indentLevel++;

            for (int i = 0; i < size; i++) {
                Shape shape = compoundShape.getShapes().get(i);
    
                if (shape instanceof CompoundShape) {
                    hasInnerCompoundShape = true;
                    indentLevel++;
                    visitCompoundShape((CompoundShape) shape);
                } 
                else {
                    
                    PrettyPrintVisitor visitor = new PrettyPrintVisitor();
                    shape.accept(visitor);
                    result += repeat("  ", indentLevel) + visitor.getResult() + "\n";
                }
            }
            indentLevel--;
            
            
            if (!hasInnerCompoundShape) {
                if(indentLevel != 0)
                result += repeat("  ", indentLevel) + "}\n";
                else
                result += repeat("  ", indentLevel) + "}";
            } else 
                result += repeat("  ", indentLevel) + "}";
            
        } else {
            result += indent + "CompoundShape {}"; 
        }
    }

    private String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    @Override
    public void visitTextedShape(TextedShape textedShape) {
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        textedShape.getShape().accept(visitor);
        result = visitor.getResult() + ", text: " + textedShape.getText();
    }

    @Override
    public void visitColoredShape(ColoredShape coloredShape) {
        String colorCode = "";
        switch (coloredShape.getColor()) {
            case "RED":
                colorCode = "\033[0;31m";
                break;
            case "GREEN":
                colorCode = "\033[0;32m";
                break;
            case "BLUE":
                colorCode = "\033[0;34m";
                break;
        }
        String resetCode = "\033[0m";
        PrettyPrintVisitor visitor = new PrettyPrintVisitor();
        coloredShape.getShape().accept(visitor);

        result = colorCode + visitor.getResult() + resetCode; 
    }

    @Override
    public String getResult() {
        return result.toString();
    }
}
