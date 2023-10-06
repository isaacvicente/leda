package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
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
		int result = 0;
		if (!isEmpty()) {
			if (node.isLeaf()) {
				result = 1;
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
		BSTNode<T> result = null;
		if (!isEmpty() && element != null) {
			if (node.isLeaf()) {
				if (node.getData().equals(element)) {
					result = node;
				}
			} else {
				if (element.compareTo(node.getData()) > 0) {
					result = search(element, (BSTNode<T>) node.getLeft());
				} else if (element.compareTo(node.getData()) < 0) {
					result = search(element, (BSTNode<T>) node.getRight());
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
				node.setLeft(new BSTNode<T>());
				node.setRight(new BSTNode<T>());
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

		if (!isEmpty()) {
			if (node.isLeaf()) {
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

		if (!isEmpty()) {
			if (node.isLeaf()) {
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

			if (node != null) {
				result = minimum((BSTNode<T>) node.getRight());
			}
		}

		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		if (element != null) {
			BSTNode<T> node = search(element);

			if (node != null) {
				result = maximum((BSTNode<T>) node.getLeft());
			}
		}

		return result;
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
