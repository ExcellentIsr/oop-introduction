package telran.utils;

import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {
	public static <T> int binarySearch(T[] array, T searchedObj, Comparator<T> comp) {
		int high = array.length - 1;
		int low = 0;
		int middle = array.length / 2;

		while (low <= high && !array[middle].equals(searchedObj)) {
			if (comp.compare(array[middle], searchedObj) > 0) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
			middle = (high + low) / 2;
		}

		return (low > high) ? -low - 1 : middle;
	}

	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int lenght = objects.length;

		do {
			lenght--;
		} while (moveMaxAtEnd(objects, lenght, comparator));
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int lenght, Comparator<T> comp) {
		boolean fl = false;
		for (int i = 0; i < lenght; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i);
				fl = true;
			}
		}
		return fl;
	}

	private static <T> void swap(T[] objects, int i) {
		T tmp = objects[i];
		objects[i] = objects[i + 1];
		objects[i + 1] = tmp;
	}
}
