package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		quickSort(array, 0, array.length - 1);
		return binarySearch(array, 0, array.length - 1, x);
	}
	
	private Integer binarySearch(Integer[] a, int left, int right, Integer k) {
		if (right - left > 1) {
			int meio = (left + right) / 2;

			if (a[meio] == k)
				return a[meio];
			
			if (k > a[meio])
				return binarySearch(a, meio + 1, right, k);
			else
				return binarySearch(a, left, meio - 1, k);
		} else {
			if (a[left] > k && a[right] > k)
				return null;
			if (a[right] <= k)
				return a[right];
			else
				return a[left];
		}
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
