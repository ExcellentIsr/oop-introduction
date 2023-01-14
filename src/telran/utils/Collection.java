package telran.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

public interface Collection<T> extends Iterable<T> {

	boolean add(T element);

	boolean remove(T pattern);

	default boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		Iterator<T> it = iterator();

		while (it.hasNext()) {
			T next = it.next();
			if (predicate.test(next)) {
				it.remove();
			}
		}

		return oldSize > size();
	}

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	default T[] toArray(T[] ar) {
		if (ar.length < size()) {
			ar = Arrays.copyOf(ar, size());
		}
		Iterator<T> it = iterator();
		int i = 0;
		while (it.hasNext()) {
			ar[i++] = it.next();
		}
		Arrays.fill(ar, size(), ar.length, null);

		return ar;
	}
}