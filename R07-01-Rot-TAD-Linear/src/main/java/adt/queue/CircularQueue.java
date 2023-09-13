package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} 
		
		int length = this.array.length;
		
		this.tail = (this.tail + 1) % length;
		this.array[this.tail] = element;
		
		this.elements++;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		this.elements--;

		int length = this.array.length;
		this.head = (this.head + 1) % length;
		T element = this.array[this.head];

		return element;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return this.array[this.head + 1];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}
