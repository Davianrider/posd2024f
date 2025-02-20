package org.ntut.posd2024f.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindShapeVisitor implements Visitor<List<Shape>> {
    private Predicate<Shape> condition;
    private List<Shape> findShapes;
    
    public FindShapeVisitor(Predicate<Shape> condition) {
        this.condition = condition;
        findShapes = new ArrayList<>();
    }

    @Override
    public void visitCircle(Circle circle) {
        if(condition.test(circle))
            findShapes.add(circle);
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        if(condition.test(rectangle))
            findShapes.add(rectangle);
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        if(condition.test(triangle))
            findShapes.add(triangle);
    }

    @Override
    public void visitConvexPolygon(ConvexPolygon convexPolygon) {
        if(condition.test(convexPolygon))
            findShapes.add(convexPolygon);
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        if(condition.test(compoundShape))
            findShapes.add(compoundShape);
        
        for (Shape shape : compoundShape.getShapes())
            shape.accept(this);
    }

    @Override
    public void visitTextedShape(TextedShape textedShape) {
        if(condition.test(textedShape))
            findShapes.add(textedShape);
        textedShape.getShape().accept(this);
    }

    @Override
    public void visitColoredShape(ColoredShape coloredShape) {
        if(condition.test(coloredShape))
            findShapes.add(coloredShape);
        coloredShape.getShape().accept(this);
    }

    public List<Shape> getResult() {
        return findShapes;
    }
}
