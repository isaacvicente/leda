package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if (!isLeaf(position) && isValidIndex(position)) {
			int max_index = max_of_three(position, left(position), right(position));

			if (max_index != position) {
				Util.swap(heap, position, max_index);
				heapify(max_index);
			}
		}
	}

	private boolean isValidIndex(int i) {
        return i >= 0 && i <= this.index;
    }
    
    private boolean isLeaf(int i) {
        return i > parent(this.index) && i <= this.index;
    }

	private int max_of_three(int n1, int n2, int n3) {
		int result;
		T a = this.heap[n1];
		T b = this.heap[n2];
		T c = this.heap[n3];

		if (a.compareTo(b) >= 0 && a.compareTo(c) >= 0) {
			result = n1;;
		} else if (b.compareTo(a) >= 0 && b.compareTo(c) >= 0) {
			result = n2;
		} else {
			result = n3;
		}

		return result;
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		
		index++;
		this.heap[index] = element;

		int i = index;
		while (i > 0 && this.heap[parent(i)].compareTo(this.heap[i]) < 0) {
			T aux = this.heap[i];
			this.heap[i] = this.heap[parent(i)];
			this.heap[parent(i)] = aux;
			i = parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		for (int i = parent(array.length - 1); i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		T result = null;
		if (!isEmpty()) {
			result = this.heap[0];
			this.heap[0] = this.heap[index];
			this.index--;

			heapify(0);
		}

		return result;
	}

	@Override
	public T rootElement() {
		T result = null;
		if (!isEmpty()) {
			result = this.heap[this.index];
		}

		return result;
	}

	@Override
	public T[] heapsort(T[] array) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
