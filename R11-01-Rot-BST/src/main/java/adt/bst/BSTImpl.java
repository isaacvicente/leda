package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(getRoot());
	}

	private int height(BSTNode<T> node) {
		int result = -1;
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				result = 0;
			} else {
				result = 1 + Math.max(height((BSTNode<T>) node.getRight()), height((BSTNode<T>) node.getLeft()));
			}
		}

		return result; 
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, getRoot());
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();
		if (!node.isEmpty() && element != null) {
			if (node.isLeaf()) {
				if (node.getData().equals(element)) {
					result = node;
				}
			} else {
				if (element.equals(node.getData())) {
					result = node;
				} else if (element.compareTo(node.getData()) > 0) {
					result = search(element, (BSTNode<T>) node.getRight());
				} else if (element.compareTo(node.getData()) < 0) {
					result = search(element, (BSTNode<T>) node.getLeft());
				}
			}
		}

		return result;
	}

	@Override
	public void insert(T element) {
		insert(element, getRoot());
	}

	private void insert(T element, BSTNode<T> node) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode<>());
				node.setRight(new BSTNode<>());
				node.getLeft().setParent(node);
				node.getRight().setParent(node);
			} else {
				if (element.compareTo(node.getData()) > 0) {
					insert(element, (BSTNode<T>) node.getRight());
				} else if (element.compareTo(node.getData()) < 0) {
					insert(element, (BSTNode<T>) node.getLeft());
				}
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> result = null;

		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				result = node;
			} else {
				result = maximum((BSTNode<T>) node.getRight());
			}
		}

		return result;
	}


	@Override
	public BSTNode<T> minimum() {
		return minimum(getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> result = null;

		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				result = node;
			} else {
				result = minimum((BSTNode<T>) node.getLeft());
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		if (element != null) {
			BSTNode<T> node = search(element);

			if (!node.isEmpty()) {
				if (node.getRight().isEmpty()) {
					result = firstGreaterThan(element, node);
				} else {
					result = minimum((BSTNode<T>) node.getRight());
				}
			}
		}

		return result;
	}

	private BSTNode<T> firstGreaterThan(T element, BSTNode<T> node) {
		BSTNode<T> result = null;

		if (node != null) {
			if (node.getData().compareTo(element) > 0) {
				result = node;
			} else {
				result = firstGreaterThan(element, (BSTNode<T>) node.getParent());
			}
		}
		
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		if (element != null) {
			BSTNode<T> node = search(element);

			if (!node.isEmpty()) {
				if (node.getLeft().isEmpty()) {
					result = firstSmallerThan(element, node);
				} else {
					if (node.getLeft().isLeaf()) {
						result = (BSTNode<T>) node.getLeft();
					} else {
						result = maximum((BSTNode<T>) node.getLeft());
					}
				}
			}
		}

		return result;
	}
	
	private BSTNode<T> firstSmallerThan(T element, BSTNode<T> node) {
		BSTNode<T> result = null;

		if (node != null) {
			if (node.getData().compareTo(element) < 0) {
				result = node;
			} else {
				result = firstSmallerThan(element, (BSTNode<T>) node.getParent());
			}
		}
		
		return result;
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);

			remove(node);
		}
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				if (node == this.root) {
					this.root = new BSTNode<>();
				} else {
					if (node.getData().compareTo(node.getParent().getData()) > 0) {
						node.getParent().setRight(new BSTNode<>());
					} else {
						node.getParent().setLeft(new BSTNode<>());
					}
				}
			} else if (hasOnlyLeftChild(node)) {
				if (node == this.root) {
					this.root = (BSTNode<T>) node.getLeft();
					this.root.setParent(new BSTNode<>());
				} else {
					node.getLeft().setParent(node.getParent());
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(node.getLeft());
					} else {
						node.getParent().setRight(node.getLeft());
					}
				}
			} else if (hasOnlyRightChild(node)) {
				if (node == this.root) {
					this.root = (BSTNode<T>) node.getRight();
					this.root.setParent(new BSTNode<>());
				} else {
					node.getRight().setParent(node.getParent());
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(node.getRight());
					} else {
						node.getParent().setRight(node.getRight());
					}
				}
			} else {
				BSTNode<T> successor = sucessor(node.getData());
				remove(successor);
				node.setData(successor.getData());
			}
		}
	}
	
	private boolean hasOnlyLeftChild(BSTNode<T> node) {
		return !node.getLeft().isEmpty() && node.getRight().isEmpty();
	}

	private boolean hasOnlyRightChild(BSTNode<T> node) {
		return node.getLeft().isEmpty() && !node.getRight().isEmpty();
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> array = new ArrayList<>();
		preOrder(array, root);
		return (T[]) array.toArray(new Comparable[size()]);
	}

	private void preOrder(List<T> array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrder(array, (BSTNode<T>) node.getLeft());
			preOrder(array, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> array = new ArrayList<>();
		order(array, root);
		return (T[]) array.toArray(new Comparable[size()]);
	}
	
	private void order(List<T> array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			order(array, (BSTNode<T>) node.getLeft());
			array.add(node.getData());
			order(array, (BSTNode<T>) node.getRight());
		}
	}
	
	@Override
	public T[] postOrder() {
		ArrayList<T> array = new ArrayList<>();
		postOrder(array, root);
		return (T[]) array.toArray(new Comparable[size()]);
	}

	private void postOrder(List<T> array, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(array, (BSTNode<T>) node.getLeft());
			postOrder(array, (BSTNode<T>) node.getRight());
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it works and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // inductive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
