package telran.utilsTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.*;

public abstract class CollectionTest {
	protected Integer[] numbers = { 10, 30, 0, 50, 20, 5, 100, 15, 40, 25, 3, -10, -9, 7, -18, -5, -15, -12, -11, -17,
			-13, -14 };
	protected Integer ar[] = new Integer[numbers.length + 100];
	protected Collection<Integer> collection;
	protected Integer[] empty = {};

	@BeforeEach
	void setUp() throws Exception {
		for (Integer num : numbers) {
			collection.add(num);
		}
	}

	abstract void testAdd();

	abstract void testIterator();

	@Test
	void testRemove() {
		Integer[] expected = { 10, 30, 0, 50, 20, 5, 15, 40, 25, 3, -9, 7, -18, -5, -12, -11, -17, -13, -14 };
		Arrays.sort(expected);

		assertTrue(collection.remove((Integer) 100));
		assertTrue(collection.remove((Integer) (-10)));
		assertTrue(collection.remove((Integer) (-15)));

		Integer[] array = collection.toArray(empty);
		Arrays.sort(array);

		assertArrayEquals(expected, array);
		assertFalse(collection.remove((Integer) 100));
		assertFalse(collection.remove((Integer) (-10)));
		assertFalse(collection.remove((Integer) (-15)));
	}

	@Test
	void testRemoveIf() {
		Integer[] expected = { 3, 5, -5, 7, -11, -13, 15, -15, -17, 25, -9 };
		Arrays.sort(expected);
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		Integer[] ggwp = collection.toArray(empty);
		Arrays.sort(ggwp);
		assertArrayEquals(expected, ggwp);
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> true));
		assertTrue(collection.isEmpty());
	}

	@Test
	void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {
		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		Arrays.sort(numbers);
		Arrays.sort(ar, 0, numbers.length);
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for (int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}

	@Test
	void removeIteratorTest() {
		Integer num = 0;

		Iterator<Integer> it = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		for (int i = 0; i < 18; i++) {
			num = it.next();
		}
		assertTrue(collection.contains(num));
		it.remove();
		assertFalse(collection.contains(num));
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());

		Iterator<Integer> it1 = collection.iterator();
		while (it1.hasNext()) {
			num = it1.next();
		}
		assertTrue(collection.contains(num));
		it1.remove();
		assertFalse(collection.contains(num));
	}
}