package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
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
			int probe = 0;
			int hash = getHash(element, probe);

			while (this.table[hash] != null) {
				if (this.table[hash].equals(element)) {
					this.table[hash] = new DELETED();
					this.elements--;
					if (probe == 1)
						this.COLLISIONS--;
					break;
				}
				probe++;
				hash = getHash(element, probe);
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty() && element != null) {
			int probe = 0;
			int hash = getHash(element, probe);

			while (this.table[hash] != null) {
				if (this.table[hash].equals(element)) {
					result = (T) this.table[hash];
					break;
				}
				probe++;
				hash = getHash(element, probe);
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

			while (this.table[hash] != null) {
				if (this.table[hash].equals(element)) {
					result = hash;
					break;
				}
				probe++;
				hash = getHash(element, probe);
			}
		}
		
		return result;
	}
	
	private int getHash(T element, int probe) {
		return ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
	}

}
