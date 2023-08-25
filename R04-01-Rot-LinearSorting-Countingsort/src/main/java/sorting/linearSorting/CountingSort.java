package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {
	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			Integer[] sorted = countingSort(array, leftIndex, rightIndex);
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = sorted[i];
			}
		}
	}

	private Integer[] countingSort(Integer[] v, int leftIndex, int rightIndex) {
		int k = v[leftIndex];
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (v[i].compareTo(k) > 0)
				k = v[i];
		}
		k++;

		Integer[] counting = new Integer[k];
		Arrays.fill(counting, 0);
		Integer[] sorted = new Integer[v.length];
		Arrays.fill(sorted, 0);

		for (int j = leftIndex; j <= rightIndex; j++) {
			counting[v[j]]++;
		}

		for (int i = 1; i < k; i++) {
			counting[i] += counting[i - 1];
		}

		for (int i = rightIndex; i >= leftIndex; i--) {
			sorted[counting[v[i]] - 1] = v[i];
			counting[v[i]]--;
		}

		return sorted;
	}
}
