package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
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

			int balance = calculateBalance(node);

			// Left-Left Case
			if (balance > 1 && element.compareTo(node.getLeft().getData()) < 0)
				Util.rightRotation(node);

			// Right-Right Case
			if (balance < -1 && element.compareTo(node.getRight().getData()) > 0)
				Util.leftRotation(node);

			// Left Right Case
			if (balance > 1 && element.compareTo(node.getLeft().getData()) > 0) {
				node.setLeft(Util.leftRotation((BSTNode<T>) node.getLeft()));
				Util.rightRotation(node);
			}

			// Right Left Case
			if (balance < -1 && element.compareTo(node.getRight().getData()) < 0) {
				node.setRight(Util.rightRotation((BSTNode<T>) node.getRight()));
				Util.leftRotation(node);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;
		if (node != null)
			result = heigth((BSTNode<T>) node.getLeft()) - heigth((BSTNode<T>) node.getRight());

		return result;
	}

	protected int heigth(BSTNode<T> node) {
		int result = -1;
		if (node != null && !node.isEmpty()) {
			if (node.isLeaf()) {
				result = 0;
			} else {
				result = 1 + Math.max(height((BSTNode<T>) node.getRight()), height((BSTNode<T>) node.getLeft()));
			}
		}

		return result;
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);

			remove(node);
//			deleteNode(node, element);
		}
	}

	private BSTNode<T> deleteNode(BSTNode<T> root, T key) {
		// STEP 1: PERFORM STANDARD BST DELETE
		if (root == null || root.isEmpty())
			return root;

		// If the key to be deleted is smaller than
		// the root's key, then it lies in left subtree
		if (key.compareTo(root.getData()) < 0)
			root.setLeft(deleteNode((BSTNode<T>) root.getLeft(), key));

			// If the key to be deleted is greater than the
			// root's key, then it lies in right subtree
		else if (key.compareTo(root.getData()) > 0)
			root.setRight(deleteNode((BSTNode<T>) root.getRight(), key));

			// if key is same as root's key, then this is the node
			// to be deleted
		else {

			// node with only one child or no child
			if ((root.getLeft().isEmpty()) || (root.getRight().isEmpty())) {
				BSTNode<T> temp = null;
				if (root.getLeft().isEmpty())
					temp = (BSTNode<T>) root.getRight();
				else
					temp = (BSTNode<T>) root.getLeft();

				// No child case
				if (temp == null)
				{
					temp = root;
					root = null;
				}
				else // One child case
					root = temp; // Copy the contents of
				// the non-empty child
			}
			else
			{

				// node with two children: Get the inorder
				// successor (smallest in the right subtree)
				BSTNode<T> temp = minimum((BSTNode<T>) root.getRight());

				// Copy the inorder successor's data to this node
				root.setData(temp.getData());

				// Delete the inorder successor
				root.setRight(deleteNode((BSTNode<T>) root.getRight(), temp.getData()));
			}
		}

		// If the tree had only one node then return
		if (root == null)
			return root;

		// STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = calculateBalance(root);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && calculateBalance((BSTNode<T>) root.getLeft()) >= 0)
			return Util.rightRotation(root);

		// Left Right Case
		if (balance > 1 && calculateBalance((BSTNode<T>) root.getLeft()) < 0) {
			root.setLeft(Util.leftRotation((BSTNode<T>) root.getLeft()));
			return Util.rightRotation(root);
		}

		// Right Right Case
		if (balance < -1 && calculateBalance((BSTNode<T>) root.getRight()) <= 0)
			return Util.leftRotation(root);

		// Right Left Case
		if (balance < -1 && calculateBalance((BSTNode<T>) root.getRight()) > 0) {
			root.setRight(Util.rightRotation((BSTNode<T>) root.getRight()));
			return Util.leftRotation(root);
		}

		return root;
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

			if (!node.getParent().isEmpty()) {
				BSTNode<T> x = (BSTNode<T>) node.getParent();
				System.out.println(node.getParent().getData());
				int balance = calculateBalance(x);
				System.out.println(balance);

				// Left-Left Case
				if (balance > 1 && calculateBalance((BSTNode<T>) x.getLeft()) >= 0) {
					System.out.println("LL case");
					Util.rightRotation(x);
				}

				// Left Right Case
				if (balance > 1 && calculateBalance((BSTNode<T>) x.getLeft()) < 0) {
					System.out.println("LR case");
					x.setLeft(Util.leftRotation((BSTNode<T>) x.getLeft()));
					Util.rightRotation(x);
				}

				// Right-Right Case
				if (balance < -1 && calculateBalance((BSTNode<T>) x.getRight()) <= 0)
					Util.leftRotation(x);

				// Right Left Case
				if (balance < -1 && calculateBalance((BSTNode<T>) x.getRight()) > 0) {
					x.setRight(Util.rightRotation((BSTNode<T>) x.getRight()));
					Util.leftRotation(x);
				}
			}
		}
	}

	private boolean hasOnlyLeftChild(BSTNode<T> node) {
		return !node.getLeft().isEmpty() && node.getRight().isEmpty();
	}

	private boolean hasOnlyRightChild(BSTNode<T> node) {
		return node.getLeft().isEmpty() && !node.getRight().isEmpty();
	}
}
