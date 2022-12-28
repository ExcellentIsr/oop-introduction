package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import telran.utils.*;

class ArrayListTest {

	@Test
	void arrayStringListTest() {
		ArrayList <String> list = new ArrayList<String>(8);
		String[] str = {"ab", "abc", "abcd", "de", "aaaaaa"};
		
		for(String s : str	) {
			list.add(s);
		}
		
//		System.out.println(list.remove(2));
//		System.out.println(list.remove("abc"));
		System.out.println(list.removeIf(t -> t.length() == 2));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
	}
	
	@Test
	@Disabled
	void arrayIntegerListTest() {
		ArrayList <Integer> list = new ArrayList<Integer>(8);
		Integer[] str = {1,2,7,-29,235,76,-12};
		Integer[] expected = {1,2,77,76,76};
		
		for(Integer s : str	) {
			list.add(s);
		}
		
		list.add(2, 77);
//		list.remove(3);
//		list.remove(235);
//		list.removeIf(t -> t < 0);
		assertFalse(list.isEmpty());
		assertTrue(list.contains(1));
		assertFalse(list.contains(0));
		assertEquals(3, list.indexOf(76));
		assertEquals(-1, list.indexOf(-29));
		assertEquals(0, list.lastIndexOf(1));
		assertEquals(-1, list.lastIndexOf(0));
		
		assertArrayEquals(expected, str);
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i) + " ");
//		}
	}

}
