package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(Integer[] a, int left, int rigth) {
		if (left < rigth) {
			int pivotInd = partition(a, left, rigth);
			quickSort(a, left, pivotInd - 1);
			quickSort(a, pivotInd + 1, rigth);
		}
		
	}

	private int partition(Integer[] a, int left, int right) {
		int pivot = a[left];
		int k = left;

		for (int i = left + 1; i <= right; i++) {
			if (a[i] <= pivot) {
				Util.swap(a, ++k, i);
			}
		}

		Util.swap(a, left, k);

		return k;
	}

}
