package telran.utilsTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.Map;

abstract class MapTest {
	Map<Integer, String> map;
	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}
	@Test
	void putTest( ) {
		assertEquals("One", map.put(1, "אחד"));
		assertEquals("אחד", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}
	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(1));
		assertTrue(map.containsKey(3));
		assertFalse(map.containsKey(0));
	}
	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("One"));
		assertTrue(map.containsValue("Three"));
		assertFalse(map.containsValue("Zero"));
	}
	@Test
	void valuesTest() {
		String[] str = {"One", "Two", "Three"};
		int[] i = {0};

		map.values().forEach(item -> assertEquals(str[i[0]++], item));
	}
	@Test
	void keySetTest() {
		Integer[] ar = {1, 2, 3};
		int[] i = {0};

		map.keySet().forEach(item -> assertEquals(ar[i[0]++], item));
	}
	@Test
	void entrySetTest() {
		String[] str = {"One", "Two", "Three"};
		Integer[] ar = {1, 2, 3};
		int[] i = {0 , 0};
		
		map.entrySet().forEach(item -> {
			assertEquals(ar[i[0]++], item.getKey());
			assertEquals(str[i[1]++], item.getValue());
		});
	}
	@Test
	void removeTest() {
		assertEquals("One", map.put(1, "אחד"));
		assertEquals("אחד", map.get(1));
		
		assertEquals("אחד", map.remove(1));
		assertNull(map.get(1));
		assertNull(map.remove(1));
		assertNull(map.put(1, "אחד"));
		assertEquals("אחד", map.get(1));
	}
}
