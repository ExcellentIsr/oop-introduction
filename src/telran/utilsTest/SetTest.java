package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.*;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));
	}

	@Override
	@Test
	void testIterator() {
		Integer[] array = new Integer[numbers.length];
		int i = 0;
		for (Integer item : set) {
			array[i++] = item;
		}
		Arrays.sort(numbers);
		Arrays.sort(array);
		assertArrayEquals(array, numbers);
	}
}