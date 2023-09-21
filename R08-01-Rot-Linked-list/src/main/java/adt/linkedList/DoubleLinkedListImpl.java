package adt.linkedList;

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
			if (size() == 1) {
				this.last = new DoubleLinkedListNode<T>();
				this.head = this.last;
			} else {
				this.head = this.head.next;
				((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
			}
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
