package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}
	
	private void desempilhaParaStack2(Stack<T> stack1, Stack<T> stack2) {
		while (!stack1.isEmpty()) {
			T element = null;
			try {
				element = stack1.pop();
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}			
			try {
				stack2.push(element);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void empilhaParaStack1(Stack<T> stack1, Stack<T> stack2) {
		while (!this.stack2.isEmpty()) {
			T element = null;
			try {
				element = this.stack2.pop();
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}			
			try {
				this.stack1.push(element);
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}

		try {
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T head = null;

		desempilhaParaStack2(stack1, stack2);

		try {
			head = this.stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}

		empilhaParaStack1(stack1, stack2);
		
		return head;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		}
		T head;

		desempilhaParaStack2(stack1, stack2);
		
		head = this.stack2.top();

		empilhaParaStack1(stack1, stack2);
		
		return head;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
