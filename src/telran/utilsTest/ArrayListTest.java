package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import telran.utils.*;

class ArrayListTest {

	@Test
//	@Disabled
	void arrayStringListTest() {
		ArrayList<String> list = new ArrayList<String>(8);
		String[] str = { "ab", "abc", "abcd", "de", "aaaab", "s", "bb", "_ccc" };

		for (String s : str) {
			list.add(s);
		}
		
		assertFalse(list.isEmpty());
		assertTrue(list.contains("s"));
		assertTrue(list.contains("abc"));
		assertEquals(4, list.indexOf("aaaab"));
		assertEquals(-1, list.indexOf("$"));
		assertEquals(5, list.lastIndexOf("s"));
		assertEquals(-1, list.lastIndexOf("%"));
		
		list.remove(2);
		list.remove("abc");
		list.removeIf(t -> t.length() == 2);
		
		String[] arrayString = new String[0];
		arrayString = list.toArray(arrayString);
		assertArrayEquals(new String[] { "aaaab", "s", "_ccc" }, arrayString);
		
		String[] arrayString1 = new String[6];
		arrayString1 = list.toArray(arrayString1);
		assertArrayEquals(new String[] { "aaaab", "s", "_ccc", null, null, null}, arrayString1);

	}

	@Test
//	@Disabled
	void arrayIntegerListTest() {
		ArrayList<Integer> list = new ArrayList<Integer>(8);
		Integer[] str = { 1, 2, 3, -1, 76, 1, -2, -3 };

		for (Integer s : str) {
			list.add(s);
		}

		assertFalse(list.isEmpty());
		assertTrue(list.contains(-1));
		assertEquals(4, list.indexOf(76));
		assertEquals(-1, list.indexOf(-76));
		assertEquals(5, list.lastIndexOf(1));
		assertEquals(-1, list.lastIndexOf(0));
		
		list.remove(0);
		list.remove((Integer)(-3));
		list.removeIf(t -> Math.abs(t) % 2 == 1);
		
		Integer[] arrayInteger = new Integer[0];
		arrayInteger = list.toArray(arrayInteger);
		assertArrayEquals(new Integer[] { 2, 76, -2 }, arrayInteger);
		
		Integer[] arrayInteger1 = new Integer[5];
		arrayInteger1 = list.toArray(arrayInteger1);
		assertArrayEquals(new Integer[] { 2, 76, -2, null, null }, arrayInteger1);
	}

}
