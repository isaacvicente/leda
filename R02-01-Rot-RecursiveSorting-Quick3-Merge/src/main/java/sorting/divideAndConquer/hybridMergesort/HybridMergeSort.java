package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((rightIndex - leftIndex) + 1 >= SIZE_LIMIT) {
			INSERTIONSORT_APPLICATIONS ++;
			insertionSort(array, leftIndex, rightIndex);
		} else {
			MERGESORT_APPLICATIONS ++;
			mergeSort(array, leftIndex, rightIndex);
		}
	}

	private void insertionSort(T[] v, int left, int right) {
		for (int i = left; i < right; i++) {
			int j = i + 1;
			while (j > left && v[j].compareTo(v[j - 1]) <= 0) {
				swap(v, j, j - 1);
				j--;
			}
		}
	}

	private void mergeSort(T[] v, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(v, left, mid);
			mergeSort(v, mid + 1, right);
			merge(v, left, right);
		}
	}

	private void merge(T[] a, int left, int right) {
		T[] h = (T[]) new Comparable[a.length];
		for (int i = left; i <= right; i++) {
			h[i] = a[i];
		}

		int mid = (left + right) / 2;
		int i = left;
		int j = mid + 1;
		int k = left;

		while (i <= mid && j <= right) {
			if (h[i].compareTo(h[j]) <= 0)
				a[k++] = h[i++];
			else
				a[k++] = h[j++];
		}

		while (i <= mid)
			a[k++] = h[i++];

		while (j <= right)
			a[k++] = h[j++];
	}
}
