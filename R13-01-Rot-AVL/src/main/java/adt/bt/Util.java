package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> y = (BSTNode<T>) node.getRight();
		BSTNode<T> T2 = (BSTNode<T>) y.getLeft();

		// Perform rotation
		y.setLeft(node);
		node.setRight(T2);

		// Return new root
		return y;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> x = (BSTNode<T>) node.left;
		BSTNode<T> T2 = (BSTNode<T>) x.getRight();


		x.setRight(node);
		node.setLeft(T2);

		return x;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
