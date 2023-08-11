package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = 0; i < array.length - 1; i++) {
			int j = i + 1;
			while (j > 0 && array[j].compareTo(array[j-1]) < 0) {
				Util.swap(array, j, j-1);
				j--;
			}
		}
	}

}
