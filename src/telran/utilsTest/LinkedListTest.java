package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.*;

public class LinkedListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
	
	@Test
	void hasLoopTest() {
		LinkedList<Integer> list1 = new LinkedList<>();
		Integer[] numbers = { 10, 100, -5, 134, 280, 120, 15 };
		for (Integer num : numbers) {
			list1.add(num);
		}
		assertFalse(list1.hasLoop());
		list1.setNext(2, 1);
		assertTrue(list1.hasLoop());
	}
}