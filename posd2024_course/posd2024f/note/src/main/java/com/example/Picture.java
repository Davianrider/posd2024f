package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngReader;

public class Picture implements Content {

    private String path;
    private Map<String, String> metadata;
    private int[][] imageData;

    public Picture(String path) {
        this.path = path;
        this.metadata = new java.util.HashMap<>();
        try {
            FileInputStream inputStream = new FileInputStream(new File(path));
            PngReader pngReader = new PngReader(inputStream);
            this.imageData = new int[pngReader.imgInfo.rows][pngReader.imgInfo.cols];
            
            pngReader.readSkippingAllRows();

            metadata.put("File name", pngReader.getMetadata().getTxtForKey("File name"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        return metadata.get(key);
    }

    public void loadImageData() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        PngReader pngReader = new PngReader(inputStream);
        ImageInfo imageInfo = pngReader.imgInfo;
        for (int row = 0; row < imageInfo.rows; row++) {
            ImageLineInt line = (ImageLineInt) pngReader.readRow(row);
            imageData[row] = line.getScanline();
        }
    }
    public int[][] getImageData() {
        return imageData;
    }

    @Override
    public void accept(ContentVisitor visitor) {
        visitor.visitPicture(this);
    }

    @Override
    public String toString() {
        return getValue("File name");
    }
}
