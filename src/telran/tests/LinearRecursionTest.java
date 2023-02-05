package telran.tests;

import static org.junit.jupiter.api.Assertions.*;
import static telran.LinearRecursion.*;

import java.lang.reflect.Array;

import org.junit.jupiter.api.Test;

import telran.MdArray;

public class LinearRecursionTest {
	@Test
	void fTest() {
		f(6);
	}

	void f(int a) {
		if (a > 5) {
			f(a - 1);
		}
	}

	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
		assertEquals(24 * 5 * 6, factorial(6));
		assertEquals(24, factorial(-4));
	}

	@Test
	void powerTest() {
		assertEquals(1000, power(10, 3));

		for (int i = 0; i < 1000; i++) {
			int a = (int) Math.random() * 1100 - 100, b = (int) Math.random() * 200 - 50;
			if (b < 0) {
				assertThrowsExactly(IllegalArgumentException.class, () -> power(a, b));
			} else {
				assertEquals(Math.pow(a, b), power(a, b));
			}
		}
	}

	@Test
	void squareTest() {
		assertEquals(1, square(1));
		
		for (int i = 0; i < 1000; i++) {
			int a = (int) Math.random() * 1100 - 100;
			assertEquals(Math.pow(a, 2), square(a));
		}
	}
	
	@Test
	void isSubstringTest() {
		assertThrowsExactly(IllegalArgumentException.class, () -> isSubstring("Hamburger", ""));
		assertTrue(isSubstring("Hamburger", "burger"));
		assertTrue(isSubstring("Hamburger", "Ham"));
		assertTrue(isSubstring("Hamburger", "r"));
		assertFalse(isSubstring("Hamburger", "ham"));
		assertFalse(isSubstring("Hamburger", "hmbur"));
	}

	@Test
	void sumTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		assertEquals(21, sum(ar));
		assertEquals(0, sum(new int[0]));
	}

	@Test
	void reverseTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		int expected[] = { 6, 5, 4, 3, 2, 1 };
		int ar1[] = { 1, 2, 3, 4, 5, 6, 7 };
		int expected1[] = { 7, 6, 5, 4, 3, 2, 1 };
		reverse(ar);
		reverse(ar1);
		assertArrayEquals(expected, ar);
		assertArrayEquals(expected1, ar1);
	}
	
	@Test
	void MdArrayTest() {
		int[] ar = {3,2,2};
		MdArray<Integer> arrayInt = new MdArray<Integer>(ar, 50);
		Integer[] sum = {0};
		arrayInt.forEach(n -> assertEquals(50, n));
		arrayInt.set(new int[] {2,1,1}, 55);
		
		arrayInt.forEach(n -> sum[0] += n);
		assertEquals(605, sum[0]);
		assertEquals(50, arrayInt.get(new int[] {2,1,0}));
		assertEquals(55, arrayInt.get(new int[] {2,1,1}));
	}
}