package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return isBST(getBSt().getRoot());
	}

	private boolean isBST(BSTNode<T> node) {
		boolean result = false;
		if (!node.isEmpty() && node.isLeaf())
			result = true;
		else {
			if (node.getRight().isLeaf() || node.getLeft().isLeaf()) {
				if (node.getRight().isLeaf()) {
					result = node.getRight().getData().compareTo(node.getData()) > 0;
				}

				if (node.getLeft().isLeaf()) {
					result = node.getLeft().getData().compareTo(node.getData()) < 0;
				}
			} else {
				result = isBST((BSTNode<T>) node.getRight()) && isBST((BSTNode<T>) node.getLeft());
			}
		}

		return result;
	}
}
