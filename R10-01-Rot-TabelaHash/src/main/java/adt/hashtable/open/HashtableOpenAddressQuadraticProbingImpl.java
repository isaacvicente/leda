package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull()) {
			throw new HashtableOverflowException();
		} else {
			if (element != null) {
				boolean found = false;
				int probe = 0;
				int hash = getHash(element, probe);

				while (this.table[hash] != null && !this.table[hash].equals(this.deletedElement)) {
					this.COLLISIONS++;
					if (this.table[hash].equals(element)) {
						found = true;
						break;
					}
					probe++;
					hash = getHash(element, probe);
				}
				
				// Só insere se não há um elemento igual na estrutura
				// (hashtable não contém duplicados)
				if (!found) {
					this.table[hash] = element;
					this.elements++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			int index =  indexOf(element);
			
			if (index != -1) {
				this.table[index] = this.deletedElement;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty() && element != null) {
			int index = indexOf(element);
			
			if (index != -1) {
				result = (T) this.table[index];
			}
		}
		
		return result;
	}

	@Override
	public int indexOf(T element) {
		int result = -1;
		if (!isEmpty() && element != null) {
			int probe = 0;
			int hash = getHash(element, probe);

			while (this.table[hash] != null && probe < this.capacity()) {
				if (this.table[hash].equals(element)) {
					result = hash;
					break;
				}

				++probe;
				hash = getHash(element, probe);
			}
		}
		
		return result;
	}
	
	private int getHash(T element, int probe) {
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
	}
}
