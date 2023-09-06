package orderStatistic;

import org.junit.Test;
import org.junit.Assert;

public class QuickSelectTest {
    @Test
    public void testQuickSelect() {

    }
    
    QuickSelect<Integer> impl = new QuickSelect<>();

	void genericTest(Integer[] arr, int k, Integer expected) {
		Integer result = impl.quickSelect(arr, k);
		Assert.assertEquals(expected, result);
	}
	
	@Test
	public void testQuickSelect1() {
		genericTest(new Integer[]{ 0, 1, 2, 3, 4, 5, 6, 7 }, 1, 0);
	}

	@Test
	public void testQuickSelect2() {
		genericTest(new Integer[]{ 0, 1, 2, 3, 5, 6, 7 }, 4, 3);
	}

	@Test
	public void testQuickSelect3() {
		genericTest(new Integer[]{ 7, 0, 2, 3, 10, 6 }, 4, 6);
	}

	@Test
	public void testQuickSelect4() {
		genericTest(new Integer[]{ 7, 0, 2, 3, 10, 6 }, 5, 7);
	}

	@Test
	public void testQuickSelect5() {
		genericTest(new Integer[]{ 7, 0, 2, 3, 10, 6 }, 10, null);
	}

	@Test
	public void testQuickSelect6() {
		genericTest(new Integer[]{ -23, -10, 5, 20, 23, 25, 29, 24 }, 2, -10);
	}

	@Test
	public void testQuickSelect7() {
		genericTest(new Integer[]{ -23, -10, 5, 20, 23, 25, 29, 24 }, 6, 24);
	}

	@Test
	public void testQuickSelect8() {
		genericTest(new Integer[]{ 10 }, 1, 10);
		genericTest(new Integer[]{ 10 }, 2, null);
		genericTest(new Integer[]{ 10 }, 3, null);
	}

	@Test
	public void testQuickSelect9() {
		genericTest(new Integer[]{ }, 1, null);
		genericTest(new Integer[]{ }, 2, null);
		genericTest(new Integer[]{ }, 3, null);
	}

	@Test
	public void testRepeated01() {
		genericTest(new Integer[]{ 5, 3, 2, 2, 1 }, 1, 1);
	}

	@Test
	public void testRepeated02() {
		genericTest(new Integer[]{ 5, 3, 2, 2, 1 }, 2, 2);
	}

	@Test
	public void testRepeated03() {
		genericTest(new Integer[]{ 5, 3, 2, 2, 1 }, 3, 2);
	}

	@Test
	public void testRepeated04() {
		genericTest(new Integer[]{ 5, 3, 2, 2, 1 }, 4, 3);
	}

	@Test
	public void testRepeated05() {
		genericTest(new Integer[]{ 1, 1, 1, 1, 2 }, 4, 1);
	}

	@Test
	public void testRepeated06() {
		genericTest(new Integer[]{ 1, 1, 1, 1, 2 }, 5, 2);
	}
}
