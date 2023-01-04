package telran.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	private class ArrayListIterator implements Iterator<T> {
		public int current = 0;

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return get(current++);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	@Override
	public boolean add(T element) {
		if (size() == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index, 0, size());
		if (size() == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = element;
		size++;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int i = 0;
		while (i < size && !res) {
			if (pattern.equals(get(i))) {
				removeElement(i);
				res = true;
			}
			i++;
		}
		return res;
	}

	@Override
	public T remove(int index) {
		checkIndex(index, 0, size());
		T res = get(index);
		removeElement(index);
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int tIndex = 0;
		for (int i = 0; i < oldSize; i++) {
			if (predicate.test(array[i])) {
				size--;
			} else {
				array[tIndex++] = array[i];
			}
		}
		Arrays.fill(array, size, oldSize, null);
		return oldSize > size;
		
	}
	
//	@Override
//	public boolean removeIf(Predicate<T> predicate) {
//		int earlySize = size;
//		for (int i = 0; i < size; i++) {
//			if (predicate.test(get(i))) {
//				removeElement(i--);
//			}
//		}
//		return earlySize > size;
//	}

	private void removeElement(int index) {
		size--;
		set(index, null);
		System.arraycopy(array, index + 1, array, index, size() - index);
		set(size(), null);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0 ? true : false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		int i = 0;
		while (i < size && !pattern.equals(get(i))) {
			i++;
		}
		return i < size();
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length >= size) {
			System.arraycopy(array, 0, ar, 0, size);
			Arrays.fill(ar, size, ar.length, null);
		} else {
			ar = Arrays.copyOf(array, size);
		}

		return ar;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;

		while (i < size && res < 0) {
			if (pattern.equals(get(i))) {
				res = i;
			}
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int i = size() - 1;
		while (i >= 0 && !pattern.equals(get(i))) {
			i--;
		}
		return i;
	}

	@Override
	public T get(int index) {
		checkIndex(index, 0, size());
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index, 0, size());
		array[index] = element;
	}
}
