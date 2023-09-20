package adt.linkedList;

import org.w3c.dom.Node;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) this.head;
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>();
		node.setData(element);
		node.setPrevious(new DoubleLinkedListNode<>());

		if (isEmpty()) {
			node.setNext(new DoubleLinkedListNode<>());
			setHead(node);
		} else {
			node.setNext(getHead());
			((DoubleLinkedListNode<T>) this.head).setPrevious(node);
			this.head = node;
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			// TODO
		}
	}

	@Override
	public void removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
