package telran.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
		int size = size();
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Iterator<T> it = iterator();
		int i = 0;
		while (it.hasNext()) {
			ar[i++] = it.next();
		}
		Arrays.fill(ar, size, ar.length, null);

		return ar;
	}

	default Stream<T> stream() {
		return StreamSupport.stream(this.spliterator(), false);
	}

	default Stream<T> parallelStream() {
		return StreamSupport.stream(this.spliterator(), true);
	}

	default T[] toArrayShuffling(T[] array) {
		T[] ar = toArray(array);
		int size = size();
		return (new Random().ints(0, size).distinct().limit(size)).mapToObj(n -> ar[n]).toArray(n -> ar);
	}
}