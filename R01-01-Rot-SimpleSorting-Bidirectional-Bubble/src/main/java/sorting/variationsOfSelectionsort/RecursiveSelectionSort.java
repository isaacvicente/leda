package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length != 0) {
			int menorInd = leftIndex;
			menorInd = menor(array, menorInd, leftIndex + 1, rightIndex);
			Util.swap(array, leftIndex, menorInd);

			if (leftIndex + 1 < rightIndex) {
				sort(array, leftIndex + 1, rightIndex);
			}
		}
	}

	public int menor(T[] array, int menorInd, int j, int rightIndex) {
		if (j == rightIndex) {
			if (array[j].compareTo(array[menorInd]) < 0) {
				return j;
			} else {
				return menorInd;
			}
		}

		if (array[j].compareTo(array[menorInd]) < 0) {
			menorInd = j;
		}
		return menor(array, menorInd, j + 1, rightIndex);
	}
}
