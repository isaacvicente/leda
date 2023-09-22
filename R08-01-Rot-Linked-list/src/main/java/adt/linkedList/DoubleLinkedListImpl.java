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
				setLast(new DoubleLinkedListNode<T>(element,
							    new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>()));
				setHead(getLast());
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

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
