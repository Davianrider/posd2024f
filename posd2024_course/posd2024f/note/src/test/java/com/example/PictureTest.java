package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngReader;

public class PictureTest {
    private static final String TEST_DATA_DIR = "src/test/resources";

    @Test
    public void testReadingTheBlueImageMetadata() throws IOException {
        // Define the path to the blue image
        String blueImagePath = TEST_DATA_DIR + "/blue_image.png";

        // Create a PngReader to read the image file
        FileInputStream inputStream = new FileInputStream(new File(blueImagePath));
        PngReader pngReader = new PngReader(inputStream);

        // Get image info
        ImageInfo imageInfo = pngReader.imgInfo;

        // Verify image dimensions and color type
        assertEquals(8, imageInfo.bitDepth);
        assertEquals(false, imageInfo.alpha);
        assertEquals(3, imageInfo.channels);

        pngReader.readSkippingAllRows();

        // PngMetadata meta = new PngMetadata(pngReader.getChunksList());
        // String value = meta.getTxtForKey("File name");
        String value = pngReader.getMetadata().getTxtForKey("File name");
        assertEquals("blue_image.png", value);


        pngReader.end();
        inputStream.close();
    }

    @Test
    public void testReadingTheBlueImageContent() throws IOException {
        // Define the path to the blue image
        String blueImagePath = TEST_DATA_DIR + "/blue_image.png";

        // Create a PngReader to read the image file
        FileInputStream inputStream = new FileInputStream(new File(blueImagePath));
        PngReader pngReader = new PngReader(inputStream);

        // Get image info
        ImageInfo imageInfo = pngReader.imgInfo;

        // Verify image dimensions and color type
        assertEquals(8, imageInfo.bitDepth);
        assertEquals(false, imageInfo.alpha);
        assertEquals(3, imageInfo.channels);

        // Read and verify pixel values
        for (int row = 0; row < imageInfo.rows; row++) {
            ImageLineInt line = (ImageLineInt) pngReader.readRow(row);
            int[] scanline = line.getScanline();
            for (int col = 0; col < imageInfo.cols; col++) {
                int r = scanline[col * 3];
                int g = scanline[col * 3 + 1];
                int b = scanline[col * 3 + 2];
                assertEquals(0, r);
                assertEquals(0, g);
                assertEquals(255, b);
            }
        }

        pngReader.end();
        inputStream.close();
    }
}
