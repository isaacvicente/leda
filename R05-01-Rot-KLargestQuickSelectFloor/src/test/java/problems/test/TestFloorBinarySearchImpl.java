package problems.test;
import static org.junit.Assert.*;

import org.junit.Test;

import problems.Floor;
import problems.FloorBinarySearchImpl;

public class TestFloorBinarySearchImpl {
    @Test
    public void testNaoPertenceArray() {
        Floor f = new FloorBinarySearchImpl();
        Integer[] a = new Integer[] {10, 8, 6, 4};
        Integer[] b = new Integer[] {10, 8, 6, 4, 2, 2};
        // 2 3 4 6 8 10
        assertEquals(6, f.floor(a, 7), 0);
        assertEquals(6, f.floor(b, 7), 0);
    }
}
