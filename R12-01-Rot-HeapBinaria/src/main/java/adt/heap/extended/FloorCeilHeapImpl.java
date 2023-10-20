package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer result = null;
		if (array != null) {
			for (Integer number : array)
				insert(number);

			result = findFloor(numero, null);
		}
		return result;
	}

	private Integer findFloor(double numero, Integer floor) {
		Integer root =  extractRootElement();
		if(root != null){
			boolean ok = floor == null || root >= floor;
			if (ok && numero >= root){
				floor = findFloor(numero, root);
			} else {
				floor = findFloor(numero, floor);
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer result = null;

		if (array != null) {
			for (Integer number: array) {
				insert(number);
			}
			result = findCeil(numero, null);
		}
		return result;
	}

	private Integer findCeil(double numero, Integer ceil) {
		Integer root = extractRootElement();
		if(root != null){
			boolean ok = ceil == null || root <= numero;
			if (ok && numero <= root){
				ceil = findCeil(numero, root);
			} else {
				ceil = findCeil(numero, ceil);
			}
		}
		return ceil;
	}
}