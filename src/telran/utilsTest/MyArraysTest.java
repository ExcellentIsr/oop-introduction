package telran.utilsTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import telran.utils.MyArrays;

class MyArraysTest {

	@Test
	void stringCompTest() {
		String[] strings = { "abcd", "bbc", "kk" };
		String[] expected = { "kk", "bbc", "abcd" };

		MyArrays.sort(strings, new StringLenghtCompare());
		assertArrayEquals(expected, strings);
	}

	@Test
	void integerCompTest() {
		Integer[] numbers = { 13, 2, -8, 47, 100, 10, 7 };
		Integer[] expected = { -8, 2, 10, 100, 47, 13, 7 };

		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}

	@Test
	void binarySearchStringTest() {
		String[] strings = { "ab", "abm", "abma", "abmc" };
		assertEquals(0, MyArrays.binarySearch(strings, "ab", new StringCompare()));
		assertEquals(-4, MyArrays.binarySearch(strings, "abmb", new StringCompare()));
	}

}
