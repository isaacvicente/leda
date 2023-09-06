package problems;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FloorBinarySearchImplTest {
    private static FloorBinarySearchImpl floorImpl;

    @Before
    public void prepareFloor() {
        floorImpl = new FloorBinarySearchImpl();
    }

    private void genericTest(Integer[] array, Integer x, Integer expected) {
        assertEquals(expected, floorImpl.floor(array, x));
    }

    @Test
    public void floorTest1() {
        this.genericTest((Integer[]) new Integer[] {4, 6, 8, 10}, 7, 6);
    }

    @Test
    public void floorTest2() {
        this.genericTest((Integer[]) new Integer[] {4, 6, 8, 10}, 8, 8);
    }

    @Test
    public void floorTest3() {
        this.genericTest((Integer[]) new Integer[] {10, 8, 6, 4}, 7, 6);
    }

    @Test
    public void floorTest4() {
        this.genericTest((Integer[]) new Integer[] {10, 8, 6, 4}, 8, 8);
    }

    @Test
    public void floorTest5() {
        this.genericTest((Integer[]) new Integer[] {4, 6, 8, 10}, 4, 4);
    }

    @Test
    public void floorTest6() {
        this.genericTest((Integer[]) new Integer[] {4, 6, 8, 10}, 5, 4);
    }

    @Test
    public void floorTest7() {
        this.genericTest((Integer[]) new Integer[] {10, 8, 6, 4}, 4, 4);
    }

    @Test
    public void floorTest9() {
        this.genericTest((Integer[]) new Integer[] {1}, 1, 1);
    }

    @Test
    public void floorTest10() {
        this.genericTest((Integer[]) new Integer[] {}, 2, null);
    }

    @Test
    public void floorTest11() {
        this.genericTest((Integer[]) new Integer[] {-1, -3, -5, -7}, -3, -3);
    }

    @Test
    public void floorTest12() {
        this.genericTest((Integer[]) new Integer[] {-1, -3, -5, -7}, -6, -7);
    }

    @Test
    public void floorTest13() {
        this.genericTest((Integer[]) new Integer[] {-7, -5, -3, -1}, -1, -1);
    }

    @Test
    public void floorTest14() {
        this.genericTest((Integer[]) new Integer[] {-7, -5, -3, -1}, -4, -5);
    }

    @Test
    public void floorTest15() {
        this.genericTest((Integer[]) new Integer[] {0}, 0, 0);
    }

    @Test
    public void floorTest16() {
        this.genericTest((Integer[]) new Integer[] {0, 1}, 20000, 1);
    }

    @Test
    public void floorTest17() {
        this.genericTest((Integer[]) new Integer[] {56, 58}, 57, 56);
    }

    @Test
    public void floorTest18() {
        this.genericTest((Integer[]) new Integer[] {0, 58}, 57, 0);
    }

    @Test
    public void floorTest19() {
        this.genericTest((Integer[]) new Integer[] {0, 58}, 57, 0);
    }

    @Test
    public void floorTest20() {
        this.genericTest((Integer[]) new Integer[] {5, 7, 9, 11}, 4, null);
    }

    @Test
    public void floorTest21() {
        this.genericTest((Integer[]) new Integer[] {-2, -3, -4}, -5, null);
    }
}