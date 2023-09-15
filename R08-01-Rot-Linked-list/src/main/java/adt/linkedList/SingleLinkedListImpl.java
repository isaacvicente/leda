package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.getData() == null;
	}

	@Override
	public int size() {
		if (isEmpty()) return 0;

		SingleLinkedListNode<T> aux = getHead();

		int i = 0;
		while (!aux.isNIL()) {
			i++;
			aux = aux.getNext();
		}

		return i;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (isEmpty()) return null;

		SingleLinkedListNode<T> aux = getHead();
		while (!aux.isNIL()) {
			if (aux.getData().equals(element)) {
				result = aux.getData();
			}
			aux = aux.getNext();
		}

		return result;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.head.setData(element);
			this.head.setNext(new SingleLinkedListNode<T>());
		} else {
			SingleLinkedListNode<T> node = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<>());
			SingleLinkedListNode<T> aux = getHead();

			while (!aux.getNext().isNIL()) {
				aux = aux.getNext();
			}

			aux.setNext(node);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = new SingleLinkedListNode<>();

			while (aux.getNext().isNIL() || aux.getNext().getData().equals(element)) {
				aux = aux.getNext();
			}
		// TODO
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];

		SingleLinkedListNode<T> aux = getHead();
		int i = 0;
		while (!aux.isNIL()) {
			array[i] = aux.getData();
			i++;
			aux = aux.getNext();
		}

		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return this.head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
