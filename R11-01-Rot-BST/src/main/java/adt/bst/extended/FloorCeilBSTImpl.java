package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		for (Integer num : array) {
			tree.insert(num);
		}

		return floor(tree.getRoot(), numero);
	}

	private Integer floor(BSTNode<Integer> node, double k) {
		Integer result = null;

		if (!node.isEmpty()) {
			Integer nodeData = node.getData();
			if (k == nodeData) {
				result = nodeData;
			} else if (k > nodeData) {
				result = nodeData;
				Integer atRight = floor((BSTNode<Integer>) node.getRight(), k);
				if (atRight != null) {
					result = atRight;
				}
			} else {
				result = floor((BSTNode<Integer>) node.getLeft(), k);
			}
		}

		return result;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		for (Integer num : array) {
			tree.insert(num);
		}

		return ceil(tree.getRoot(), numero);
	}

	private Integer ceil(BSTNode<Integer> node, double k) {
		Integer result = null;

		if (!node.isEmpty()) {
			Integer nodeData = node.getData();
			if (k == nodeData) {
				result = nodeData;
			} else if (k < nodeData) {
				result = nodeData;
				Integer atLeft = ceil((BSTNode<Integer>) node.getLeft(), k);
				if (atLeft != null) {
					result = atLeft;
				}
			} else {
				result = ceil((BSTNode<Integer>) node.getRight(), k);
			}
		}

		return result;
	}
}