package adt.bst.extended;

import junit.framework.TestCase;

public class FloorCeilBSTImplTest extends TestCase {
    FloorCeilBST getImpl() {
        return new FloorCeilBSTImpl();
    }

    public void testFloor() {
        assertNull(getImpl().floor(new Integer[]{  }, 10));
        assertNull(getImpl().floor(new Integer[]{ 11, 12, 13 }, 10));

        assertEquals(Integer.valueOf(10), getImpl().floor(new Integer[]{ 10, 11, 12, 13 }, 10));
        assertEquals(Integer.valueOf(10), getImpl().floor(new Integer[]{ 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13 }, 10));
        assertEquals(Integer.valueOf(9), getImpl().floor(new Integer[]{ 1, 7, 8, 2, 3, 4, 6, 9, 11, 12, 13 }, 10));
        assertEquals(Integer.valueOf(8), getImpl().floor(new Integer[]{ 1, -3, 100, 5, 3, -23, 8 }, 10));
    }

    public void testCeil() {
        assertNull(getImpl().ceil(new Integer[]{  }, 10));
        assertNull(getImpl().ceil(new Integer[]{ 7, 8, 9 }, 10));

        assertEquals(Integer.valueOf(10), getImpl().ceil(new Integer[]{ 10, 11, 12, 13 }, 10));
        assertEquals(Integer.valueOf(10), getImpl().ceil(new Integer[]{ 1, 2, 3, 4, 6, 7, 8, 9, 10, 11, 12, 13 }, 10));
        assertEquals(Integer.valueOf(11), getImpl().ceil(new Integer[]{ 1, 7, 8, 2, 3, 4, 6, 9, 11, 12, 13 }, 10));
        assertEquals(Integer.valueOf(100), getImpl().ceil(new Integer[]{ 1, -3, 100, 5, 3, -23, 8 }, 10));
    }
}