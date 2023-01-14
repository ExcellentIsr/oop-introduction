package telran.utils;

public interface List<T> extends Collection<T> {
	
	void add(int index, T element);
	T remove(int index);
	int indexOf(T pattern);
	int lastIndexOf(T pattern);
	T get(int index);
	void set(int index, T element);
	default void checkIndex(int index, int min, int max) {
		if (index < min || index > max) {
			throw new IndexOutOfBoundsException(index);
		}
	}
	
	@Override
	default boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}
	default public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			remove(index);
			res = true;
		}
		return res;
	}
}