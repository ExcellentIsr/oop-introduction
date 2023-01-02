package telran.utils;

import java.util.Arrays;
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
	
	public static<T> T[] filter(T[] array, Predicate<T> predicate) {
		T[] res = Arrays.copyOf(array, array.length);
		int index = 0;
		for(T element: array) {
			if(predicate.test(element)) {
				res[index++] = element;
			}
		}
		return Arrays.copyOf(res, index);
	}
	
	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		return filter(array, predicate.negate());
	}
	
	public static <T> T[] removeRepeated(T[] array) {
		T[] helper = Arrays.copyOf(array, array.length);
		Arrays.fill(helper, null);
		int index = 0;
		for(T element : array) {
			if (!contains(helper, element)) {
				helper[index++] = element;
			}
		}
		return Arrays.copyOf(helper, index);
	}
	
	public static <T> boolean contains(T[] array, T pattern) {
		int index = 0;
		while(index < array.length && !isEqual(array[index], pattern)) {
			index++;
		}
		
		return index < array.length;
	}
	
	static private boolean isEqual(Object element, Object pattern) {
		return element != null ? element.equals(pattern) : null == pattern;
	}
}
