package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import problems.Floor;
import problems.FloorBinarySearchImpl;

public class TestFloorBinarySearchImpl {
    @Test
    public void testNaoPertenceArray() {
        Floor f = new FloorBinarySearchImpl();
        Integer[] a = new Integer[] {4,6,8,10};
        assertTrue(6 == f.floor(a, 7));
    }
    
}

