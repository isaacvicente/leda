package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
		
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.data == null) {
				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
				this.data = element;
				this.next = node;
				node.previous = this;

				if (size() == 0) {
					this.previous = new RecursiveDoubleLinkedListImpl<>();
					this.previous.next = this;
				}
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.data == null) {
				this.insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();

				node.setData(this.data);
				this.setData(element);
				node.setNext(this.next);
				this.setNext(node);
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(node);
				node.setPrevious(this);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.data.equals(element)) {
				if (this.previous == null || this.previous.getData() == null) {
					this.removeFirst();
				} else if (this.next.getData() == null) {
					this.removeLast();
				} else {
					this.previous.next = this.getNext();
					((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this.previous);
				}
			} else if (this.data != null) {
				this.next.remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.previous == null && this.next == null) {
				this.data = null;
			} else {
				this.data = this.next.getData();
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
				this.next = this.next.getNext();
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next.data == null) {
				this.data = null;
				this.next = null;

				if (this.previous != null && this.previous.data == null) {
					this.previous = null;
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
