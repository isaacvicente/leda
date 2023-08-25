package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import java.util.Arrays;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivotInd = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivotInd - 1);
			sort(array, pivotInd + 1, rightIndex);
		}
	}

	private int partition(T[] v, int left, int right) {
		int pivotInd = pickPivotIndex(v, left, right);
		swap(v, left, pivotInd);

		T pivot = v[left];
		int i = left;

		for (int j = i + 1; j <= right; j++) {
			if (v[j].compareTo(pivot) <= 0) {
				swap(v, ++i, j);
			}
		}

		swap(v, i, left);
		return i;
	}

	public int pickPivotIndex(T[] values, int left, int right) {
		int mid = (left + right) / 2;

		T[] sorted = (T[]) new Comparable[]{values[left], values[mid], values[right]};
		Arrays.sort(sorted);

		if (sorted[1] == values[left]) return left;
		else if (sorted[1] == values[mid]) return mid;
		else return right;
	}

	private void swap(T[] a, int i, int j) {
		T aux = a[i];
		a[i] = a[j];
		a[j] = aux;
	}
}
