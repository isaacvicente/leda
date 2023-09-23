package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() { }

	public RecursiveSingleLinkedListImpl(T data) {
		this.data = data;
	}

	@Override
	public boolean isEmpty() {
		return getData() == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		
		return 1 + this.next.size();
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		}
		if (getData() == element) {
			return element;
		}
		
		return getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			setData(element);
			setNext(new RecursiveSingleLinkedListImpl<T>());
		} else {
			getNext().insert(element);
			
		}
	}

	@Override
	public void remove(T element) {
		recursiveRemove(element, true);
	}

	private void recursiveRemove(T element, boolean firstElement) {
		if (!isEmpty()) {
			if (size() == 1) {
				if (getData().equals(element)) {
					setData(null);
					setNext(null);
				} else {
					recursiveRemove(element, false);
				}
			} else {
				if (!getNext().isEmpty()) {
					if (firstElement && getData().equals(element)) {
						setData(getNext().getData());
						setNext(getNext().getNext());
					} else {
						if (getNext().getData().equals(element)) {
							setNext(getNext().getNext());
						} else {
							recursiveRemove(element, false);
						}
					}
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		int i = 0;
		recursiveToArray(result, i);
		return result;
	}
	
	private void recursiveToArray(T[] array, int i) {
		if (!isEmpty()) {
			array[i++] = getData();
			getNext().recursiveToArray(array, i);
		}
	}	

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
