package org.ntut.posd2024f.shapes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SortTest {

    Shape shape1 = new Circle(2);
    Shape shape2 = new Circle(4);

    @Test
    public void ByAreaAscending(){
        assertEquals(Sort.BY_AREA_ASCENDING.compare(shape1, shape2), -1);
        assertEquals(Sort.BY_AREA_ASCENDING.compare(shape2, shape1), 1);
        assertEquals(Sort.BY_AREA_ASCENDING.compare(shape1, shape1), 0);
    }

    @Test
    public void ByAreaDecending(){
        assertEquals(Sort.BY_AREA_DESCENDING.compare(shape2, shape1), -1);
        assertEquals(Sort.BY_AREA_DESCENDING.compare(shape1, shape2), 1);
        assertEquals(Sort.BY_AREA_DESCENDING.compare(shape1, shape1), 0);
    }

    @Test
    public void ByPerimeterAscending(){
        assertEquals(Sort.BY_PERIMETER_ASCENDING.compare(shape1, shape2), -1);
        assertEquals(Sort.BY_PERIMETER_ASCENDING.compare(shape2, shape1), 1);
        assertEquals(Sort.BY_PERIMETER_ASCENDING.compare(shape1, shape1), 0);
    }

    @Test
    public void ByPerimeterDescending(){
        assertEquals(Sort.BY_PERIMETER_DESCENDING.compare(shape2, shape1), -1);
        assertEquals(Sort.BY_PERIMETER_DESCENDING.compare(shape1, shape2), 1);
        assertEquals(Sort.BY_PERIMETER_DESCENDING.compare(shape1, shape1), 0);
    }

}