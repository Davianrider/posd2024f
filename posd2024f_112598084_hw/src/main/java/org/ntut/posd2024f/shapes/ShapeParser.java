package org.ntut.posd2024f.shapes;

import java.io.File;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShapeParser {
    private File file;
    private List<Shape> shapes;
    private ShapeBuilder shapeBuilder;

    Shape shape = null;
    public ShapeParser(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found");
        }
        this.file = file;
        this.shapes = new ArrayList<>();
        this.shapeBuilder = new ShapeBuilder();
    }

    public void parse() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                parseShape(line, scanner);
            }
            shapes.addAll(shapeBuilder.getResult());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    public List<Shape> getResult() {
        return shapes;
    }

    private void parseShape(String line, Scanner scanner) {
        if (line.startsWith("Circle")) {
            parserCircle(line);
         }
        else if (line.startsWith("Rectangle")) {
            parserRectangle(line);

        } else if (line.startsWith("Triangle")) {
            parserTriangle(line);

        } else if (line.startsWith("ConvexPolygon")) {
            parserConvexPolygon(line);

        } else if (line.startsWith("CompoundShape")) {
            parserCompoundShape(line, scanner);

        } else {
            throw new IllegalArgumentException("Unknown shape: " + line);
        }
    }

    private void parserCircle(String line){
        String[] tokens = line.split(",");
        String[] shapeTokens = tokens[0].split(" ");
        double radius = Double.parseDouble(shapeTokens[1]);
        String color = null;
        String text = null;

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].trim().startsWith("color=")) {
                color = tokens[i].trim().substring(6);
            } else if (tokens[i].trim().startsWith("text=")) {
                text = tokens[i].trim().substring(5);
            }
        }

        shapeBuilder.buildCircle(radius, color, text);
    }
    
    private void parserRectangle(String line){
        String color = null;
        String text = null;

        String[] tokens = line.split(" ");
        double length = Double.parseDouble(tokens[1]);
        double width = Double.parseDouble(tokens[2].replace(",", "").trim());

        if (tokens.length > 3) {
            for (int i = 3; i < tokens.length; i++) {
                if (tokens[i].startsWith("color=")) {
                    color = tokens[i].substring(6).replace(",", "").trim();
                }
                if (tokens[i].startsWith("text=")) {
                    text = line.substring(line.indexOf("text=") + 5, line.length()).replace(",", "").trim();
                }
            }
        }
        shapeBuilder.buildRectangle(length, width, color, text);
    }

    private void parserTriangle(String line){
        String[] tokens = line.split(" ");
        List<TwoDimensionalVector> vectors = parseVectors(tokens);

        String color = null;
        String text = null;
        if (tokens.length > 4) {
            for (int i = 2; i < tokens.length; i++) {
                if (tokens[i].startsWith("color=")) {
                    color = tokens[i].substring(6).replace(",", "").trim();
                }
                if (tokens[i].startsWith("text=")) {
                    text = line.substring(line.indexOf("text=") + 5, line.length()).replace(",", "").trim();
                }
            }
        }
        shapeBuilder.buildTriangle(vectors, color, text);
    }

    private void parserConvexPolygon(String line){
        String[] tokens = line.split(" ");
        List<TwoDimensionalVector> vectors = parseVectors(tokens);
        String color = null;
        String text = null;
        if (tokens.length > 5) {
            for (int i = 5; i < tokens.length; i++) {
                if (tokens[i].startsWith("color=")) {
                    color = tokens[i].substring(6).replace(",", "").trim();
                }
                if (tokens[i].startsWith("text=")) {
                    text = line.substring(line.indexOf("text=") + 5, line.length()).replace(",", "").trim();
                }
            }
        }
        shapeBuilder.buildConvexPolygon(vectors, color, text);
    }

    private List<TwoDimensionalVector> parseVectors(String[] tokens) {
        List<TwoDimensionalVector> vectors = new ArrayList<>();
        for (int i = 1; i < tokens.length; i++) {
            
            if (tokens[i].startsWith("color=") || tokens[i].startsWith("text=")) {
                break;
            }

            if (!tokens[i].startsWith("[")) throw new IllegalArgumentException("Expected token '['");
            if (!tokens[i].contains("]")) throw new IllegalArgumentException("Expected token ']'");
            if (!tokens[i].contains(",")) throw new IllegalArgumentException("Expected token ','");
        
            try {
                String vectorStr = tokens[i].replace(",", " ").trim();
                vectorStr = vectorStr.substring(1, tokens[i].length() - 1);
                String[] coordinates = vectorStr.split(" ");
                if (coordinates.length != 2) {
                    throw new IllegalArgumentException("Invalid vector format: " + tokens[i]);
                }

                int x = Integer.parseInt(coordinates[0].replaceAll("]$", ""));
                int y = Integer.parseInt(coordinates[1].replaceAll("]$", ""));
                vectors.add(new TwoDimensionalVector(x, y));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse vector: " + tokens[i], e);
            }
        }
        return vectors;
    }
        
    private void parserCompoundShape(String line, Scanner scanner) {
        if (line.endsWith("{}")) {
            shapeBuilder.beginBuildCompoundShape(null, null);
            shapeBuilder.endBuildCompoundShape();
            return;
        }
        
        if (!line.endsWith("{")) {
            if (line.endsWith("{}")) {
                shapeBuilder.beginBuildCompoundShape(null, null);
                shapeBuilder.endBuildCompoundShape();
            }else{
                throw new IllegalArgumentException("Expected token '{'");
            }
        }

        String[] compoundTokens = line.split(" ");
        String color = null;
        String text = null;

        if (compoundTokens.length > 2) {
            for (int i = 2; i < compoundTokens.length; i++) {
                if (compoundTokens[i - 1].startsWith("color=")) {
                    color = compoundTokens[i - 1].substring(6).replace(",", "").trim();
                }
                if (compoundTokens[i - 1].startsWith("text=")) {
                    text = line.substring(line.indexOf("text=") + 5, line.length() - 1).replace(",", "").trim();
                }
            }
        }
        shapeBuilder.beginBuildCompoundShape(color, text);

        int braceCount = 1;
        while (scanner.hasNextLine()) {
            String innerLine = scanner.nextLine().trim();

            if (innerLine.equals("{")) {
               braceCount++; 

            } else if (innerLine.equals("}")) {
                braceCount--;
                shapeBuilder.endBuildCompoundShape();
                if (braceCount == 0)return;
            
            } else if (!innerLine.isEmpty()){
                parseShape(innerLine, scanner);
            }
        }
        throw new IllegalArgumentException("Expected token '}'");
    }
}