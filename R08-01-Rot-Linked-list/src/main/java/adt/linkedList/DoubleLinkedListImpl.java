package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super.head = new DoubleLinkedListNode<T>();
		this.last = new DoubleLinkedListNode<>();
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				DoubleLinkedListNode<T> rawNode = new DoubleLinkedListNode<>();
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, rawNode, rawNode);
				setLast(newNode);
				setHead(newNode);
			} else {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element,
				new DoubleLinkedListNode<>(), this.last);
	
				getLast().setNext(newNode);
				setLast(newNode);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				insert(element);
			} else {
				DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element,
				getHead(), new DoubleLinkedListNode<>());
				getHead().setPrevious(newNode);
				setHead(newNode);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (size() == 1) {
				setLast(new DoubleLinkedListNode<T>());
				setHead(getLast());
			} else {
				this.head = this.head.next;
				((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (size() == 1) {
				removeFirst();
			} else {
				this.last = this.last.getPrevious();
				this.last.setNext(new DoubleLinkedListNode<T>());
			}
		}
	}
	
	@Override
	public DoubleLinkedListNode<T> getHead() {
		return (DoubleLinkedListNode<T>) this.head;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
