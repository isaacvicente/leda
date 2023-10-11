package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bt.BTNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(Integer.valueOf(6), tree.minimum().getData());
		assertEquals(Integer.valueOf(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(Integer.valueOf(6), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(Integer.valueOf(-34), tree.minimum().getData());
		assertEquals(Integer.valueOf(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(Integer.valueOf(-34), tree.sucessor(-40).getData());

		assertEquals(Integer.valueOf(-40), tree.predecessor(-34).getData());
		assertEquals(Integer.valueOf(0), tree.sucessor(-34).getData());

		assertEquals(Integer.valueOf(-34), tree.predecessor(0).getData());
		assertEquals(Integer.valueOf(2), tree.sucessor(0).getData());

		assertEquals(Integer.valueOf(0), tree.predecessor(2).getData());
		assertEquals(Integer.valueOf(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232
		Integer element = null;

		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		Arrays.sort(array);
		assertArrayEquals(array, tree.order());
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(array));

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			assertArrayEquals(list.toArray(new Integer[0]), tree.order());
			element = tree.getRoot().getData();
			tree.remove(element);
			list.remove(element);
			assertArrayEquals(list.toArray(new Integer[0]), tree.order());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(0);
		order = new Integer[] { -40, -34, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(12);
		order = new Integer[] { -40, -34, 2, 5, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(23);
		order = new Integer[] { -40, -34, 2, 5, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));
	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(Integer.valueOf(-40), tree.search(-40).getData());
		assertEquals(Integer.valueOf(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}

	@Test
	public void testMyRemove() {
		BST<Integer> tree = new BSTImpl<>();
		tree.insert(6);

		tree.insert(0);
		tree.insert(-1);
		tree.insert(1);

		tree.insert(10);
		tree.insert(9);
		tree.insert(11);

		tree.remove(6);
	}

	@Test
	public void testMySucessor() {
		BST<Integer> tree = new BSTImpl<>();
		tree.insert(15);
		tree.insert(20);

		tree.insert(6);
		tree.insert(3);
		tree.insert(2);
		tree.insert(4);

		tree.insert(7);
		tree.insert(13);
		tree.insert(9);

		assertEquals(Integer.valueOf(15), tree.sucessor(13).getData());

		tree.insert(14);

		assertEquals(Integer.valueOf(14), tree.sucessor(13).getData());
	}
}
