package adt.bst;

import java.util.Arrays;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean equals(BSTNode<T> n1, BSTNode<T> n2) {
		boolean result = false;
		boolean leftEquals = false;
		boolean rightEquals = false;

		if (n1 != null && n2 != null) {
			if (n1.isEmpty() && n2.isEmpty()) {
				result = true;
			} else if (n1.isLeaf() && n2.isLeaf()) {
				result = n1.equals(n2);
			} else {
				leftEquals = equals((BSTNode<T>) n1.getLeft(), (BSTNode<T>) n2.getLeft());
				rightEquals = equals((BSTNode<T>) n1.getRight(), (BSTNode<T>) n2.getRight());
				result = leftEquals && rightEquals;
			}
		}

		return result;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> n1, BSTNode<T> n2) {
		boolean result = false;
		boolean leftSimilar = false;
		boolean rightSimilar = false;

		if (n1 != null && n2 != null) {
			if ((n1.isEmpty() && n2.isEmpty()) || (n1.isLeaf() && n2.isLeaf())) {
				result = true;
			} else {
				leftSimilar = isSimilar((BSTNode<T>) n1.getLeft(), (BSTNode<T>) n2.getLeft());
				rightSimilar = isSimilar((BSTNode<T>) n1.getRight(), (BSTNode<T>) n2.getRight());
				result = leftSimilar && rightSimilar;
			}
		}

		return result;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		return orderStatistic((BSTNode<T>) tree.getRoot(), k);
	}

	private T orderStatistic(BSTNode<T> node, int k) {
		T result = null;
		if (!node.isEmpty() && k > 0) {
			int p = size((BSTNode<T>) node.getLeft()) + 1;
			if (p == k) {
				result = node.getData();
			} else if (k < p) {
				result = orderStatistic((BSTNode<T>) node.getLeft(), k);
			} else {
				result = orderStatistic((BSTNode<T>) node.getRight(), k - p);
			}
		}

		return result;
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		if (!node.isEmpty()) {
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
