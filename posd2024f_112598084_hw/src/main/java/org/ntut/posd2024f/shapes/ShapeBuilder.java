package org.ntut.posd2024f.shapes;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;


public class ShapeBuilder {
    private List<Shape> shapes;
    private Stack<Shape> stack;

    public ShapeBuilder() {
        shapes = new ArrayList<Shape>();
        stack = new Stack<Shape>();
    }
    
    public void buildCircle(double radius, String color, String text) {
        Shape circle = new Circle(radius);      
            
        if (color != null) {
            circle = new ColoredShape(circle, color);
        }
        if (text != null) {
            circle = new TextedShape(circle, text);
        }
        if (stack.empty()){
            shapes.add(circle);
        }
        else{
            Shape comShape = (Shape)stack.pop();
            comShape.add(circle);
            stack.push(comShape);
        }
    }

    public void buildRectangle(double length, double width, String color, String text) {
        Shape rectangle = new Rectangle(length, width);
        
        if (color != null){
            rectangle = new ColoredShape(rectangle, color);
        }
        if (text != null){
            rectangle = new TextedShape(rectangle, text);
        }
        if(stack.empty()){
            shapes.add(rectangle);
        }
        else{
            Shape comShape = (Shape)stack.pop();
            comShape.add(rectangle);
            stack.push(comShape);
        }
    }

    public void buildTriangle(List<TwoDimensionalVector> vectors, String color, String text) {
        Shape triangle = new Triangle(vectors);
        
        if (color != null) {
            triangle = new ColoredShape(triangle, color);
        }   
        if (text != null){
            triangle = new TextedShape(triangle, text);
        }
        if (stack.empty()){
            shapes.add(triangle);
        }
        else{
            Shape comShape = (Shape)stack.pop();
            comShape.add(triangle);
            stack.push(comShape);
        }
    }

    public void buildConvexPolygon(List<TwoDimensionalVector> vectors, String color, String text) {
        Shape convexPolygon = new ConvexPolygon(vectors);
        
        if (color != null){
            convexPolygon = new ColoredShape(convexPolygon, color);
        }
        if (text != null){
            convexPolygon = new TextedShape(convexPolygon, text);
        }
        if (stack.empty()){
            shapes.add(convexPolygon);
        }
        else{
            Shape comShape = (Shape)stack.pop();
            comShape.add(convexPolygon);
            stack.push(comShape);
        }
    }

    public void beginBuildCompoundShape(String color, String text) {
        Shape compoundShape = new CompoundShape();

        if (color != null){
            compoundShape = new ColoredShape(compoundShape, color);
        }
        if (text != null){
            compoundShape = new TextedShape(compoundShape, text);
        }
        stack.push(compoundShape);
    }

    public void endBuildCompoundShape() {
        if(stack.empty())
            return;
        
        Shape comShape = stack.pop();
        if (stack.empty()){
            shapes.add(comShape);
        }
        else{
            Shape parent = stack.pop();
            parent.add(comShape);
            stack.push(parent);
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }
}
