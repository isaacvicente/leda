package orderStatistic;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class KLargestOrderStatisticsImplTest {
    KLargestOrderStatisticsImpl<Integer> kth = new KLargestOrderStatisticsImpl<>();
    
    void genericTest(Integer[] arr, int k, Integer[] expected) {
		Assert.assertArrayEquals(expected, kth.getKLargest(arr, k));
	}

    @Test
    public void testGetKLargest1() {
       genericTest(new Integer[]{ 0, 1, 2, 3, 4, 5, 6, 7 }, 1, new Integer[]{ 7 }); 
    }

    @Test
    public void testGetKLargest2() {
       genericTest(new Integer[]{ 4, 1, 2, 6, 3, 5, 7, 0 }, 1, new Integer[]{ 7 }); 
    }

    @Test
    public void testGetKLargest3() {
       genericTest(new Integer[]{ 4, 1, 2, 6, 3, 5, 7, 0 }, 2, new Integer[]{ 7, 6 }); 
    }

    @Test
    public void testGetKLargest4() {
       genericTest(new Integer[]{ 0, 1, 2 }, 3, new Integer[]{ 2, 1, 0 }); 
    }

    @Test
    public void testGetKLargest5() {
       genericTest(new Integer[]{ 0, 1, 2 }, 5, new Integer[]{});
    }

    @Test
    public void testGetKLargest6() {
       genericTest(new Integer[]{ 0, 1, 2 }, 10, new Integer[]{});
    }

    @Test
    public void testOrderStatistics1() {
       assertEquals(kth.orderStatistics(new Integer[]{ 4, 1, 2, 6, 3, 5, 7, 0 }, 8), Integer.valueOf(7));
       assertEquals(kth.orderStatistics(new Integer[]{ 4, 1, 2, 6, 3, 5, 7, 0 }, 7), Integer.valueOf(6));
    }

    @Test
    public void testOrderStatistics2() {
       assertEquals(kth.orderStatistics(new Integer[]{ 4, 1, 2, 6, 3, 5, 7, 0 }, 9), null);
    }

    @Test
    public void testGetKLargest7() {
        genericTest(new Integer[]{ 1, 1, 1, 2, 3, 4 }, 4, new Integer[]{ 4, 3, 2, 1 });
    }

    @Test
    public void testGetKLargest8() {
        genericTest(new Integer[]{ 1, 1, 1, 2, 3, 4 }, 5, new Integer[]{ 4, 3, 2, 1, 1 });
    }

    @Test
    public void testGetKLargest9() {
        genericTest(new Integer[]{ 1, 1, 1, 1 }, 3, new Integer[]{ 1, 1, 1 });
    }
}
