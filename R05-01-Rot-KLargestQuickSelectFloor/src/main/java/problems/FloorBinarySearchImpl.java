package problems;

import util.Util;

import java.util.Objects;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		quickSort(array, 0, array.length - 1);
		return binarySearchWithFloor(array, 0, array.length - 1, x);
	}
	
	private Integer binarySearchWithFloor(Integer[] a, int left, int right, Integer k) {
		Integer floor = null;
		if (left <= right) {
			int meio = (left + right) / 2;

			if (Objects.equals(a[meio], k)) {
				return a[meio];
			}

			if (a[meio] < k) {
				floor = a[meio];
				Integer resto = binarySearchWithFloor(a, meio + 1, right, k);
				if (resto != null)
					floor = resto;
			}
			else {
				floor = binarySearchWithFloor(a, left, meio - 1, k);
			}
		}

		return floor;
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
