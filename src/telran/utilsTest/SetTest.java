package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

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
		Integer[] array = new Integer [numbers.length];
		int i = 0;
		Iterator<Integer> it1 = set.iterator();
		while (it1.hasNext()) {
			array[i++] = it1.next();
		}
		Arrays.sort(numbers);
		Arrays.sort(array);
		for(i = 0; i < array.length; i++) {
			assertEquals(numbers[i], array[i]);
		}
		
		Integer num;
		Iterator<Integer> it = set.iterator();
		
		for (int j = 0; j < 2; j++) {
			num = it.next();
			assertTrue(collection.contains(num));
			it.remove();
			assertFalse(collection.contains(num));
		}
	}
}