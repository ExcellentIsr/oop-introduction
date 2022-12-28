package telran.utils;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size;

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	@Override
	public boolean add(T element) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = element;
		return true;
	}

	@Override
	public void add(int index, T element) {
		checkIndex(index);
		if (size == array.length) {
			reallocate();
		}
		System.arraycopy(array, index, array, index + 1, size() - index);
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

		while (i < size() && !res) {
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
		checkIndex(index);
		T res = get(index);
		removeElement(index);
		return res;
	}


	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		int index = 0;
		for(T element : array) {
			if (predicate.test(element)) {
				removeElement(index);
				res = true;
			}
			index++;
		}
		return res;
	}
	
	private void removeElement(int index) {
		if(index == size()) {
			array[index] = null;
		}else{
			System.arraycopy(array, index + 1, array, index, size() - index);
		}
		size--;
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
		boolean res = false;
		int i = 0;

		while (i < size() && !res) {
			if (pattern.equals(get(i))) {
				res = true;
			}
			i++;
		}
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(T pattern) {
		int res = -1;
		int i = 0;

		while (i < size() && res < 0) {
			if (pattern.equals(get(i))) {
				res = i;
			}
			i++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int res = -1;
		int i = size();

		while (i > 0 && res < 0) {
			if (pattern.equals(get(i))) {
				res = i;
			}
			i--;
		}
		return res;
	}

	@Override
	public T get(int index) {
		checkIndex(index);
		return array[index];
	}

	@Override
	public void set(int index, T element) {
		checkIndex(index);
		array[index] = element;
	}
	

	private void checkIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}
	}

}
