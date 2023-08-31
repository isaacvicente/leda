package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {
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
		int menor = pegaMenor(v, leftIndex, rightIndex);
		int maior = pegaMaior(v, leftIndex, rightIndex);

		Integer[] counting;
		int k = 0;
		if (menor < 0 && maior < 0) {
			counting = new Integer[Math.abs(menor)];
			k = Math.abs(menor);
		}
		else if (menor < 0 && maior == 0) {
			counting = new Integer[Math.abs(menor) + 1];
			k = Math.abs(menor) + 1;
		}
		else if (menor < 0 && maior > 0) {
			counting = new Integer[Math.abs(menor) + 1 + maior];
			k = Math.abs(menor);
		}
		else if (menor == 0 && maior > 0) {
			counting = new Integer[maior + 1];
			k = 1;
		}
		else
			counting = new Integer[maior];

		Arrays.fill(counting, 0);
		Integer[] sorted = new Integer[v.length];
		Arrays.fill(sorted, 0);

		for (int j = leftIndex; j <= rightIndex; j++) {
			counting[v[j] - 1 + k]++;
		}

		for (int i = 1; i < counting.length; i++) {
			counting[i] += counting[i - 1];
		}

		for (int i = rightIndex; i >= leftIndex; i--) {
			sorted[counting[v[i] - 1 + k] - 1] = v[i];
			counting[v[i] - 1 + k]--;
		}

		return sorted;
	}

	private int pegaMenor(Integer[] a, int left, int right) {
		int menor = a[left];
		for (int i = left + 1; i <= right; i++) {
			if (a[i] < menor) {
				menor = a[i];
			}
		}

		return menor;
	}

	private int pegaMaior(Integer[] a, int left, int right) {
		int maior = a[left];
		for (int i = left + 1; i <= right; i++) {
			if (a[i] > maior) {
				maior = a[i];
			}
		}

		return maior;
	}

}
