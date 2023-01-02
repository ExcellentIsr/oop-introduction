package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.utils.MyArrays;

class MyArraysTest {
	Integer numbers[] = {13, 2, -8, 47, 100, 10, -7, 7, 13, 47, 7, -7};
	static final int N_NUMBERS = 10000;
	static final int N_RUNS = 1000;
	String strings[] = {
			"ab", "abm", "abmb", "abmbc"	
		};
	
	@Test
	void removeIfTest() {
		Integer expected[] = {2, -8, 100, 10};
		assertArrayEquals(expected, MyArrays.removeIf(numbers, n -> n % 2 != 0));
	}
	@Test
	void removeRepeated() {
		Integer expected[] = {13, 2, -8, 47, 100, 10, -7, 7};
		assertArrayEquals(expected, MyArrays.removeRepeated(numbers));
		String strings [] = {"aaa", "cccc", "aaa", "aaa"};
		assertArrayEquals(new String[] {"aaa", "cccc"},MyArrays.removeRepeated(strings) );
		Integer[] numbersRepeatedValues = { 13, 13, 2, -8, -8, 47, 100, 100, 100, 10, 7, 7 , 13};
		Integer expected2[] = { 13,  2, -8,  47, 100, 10, 7};
		assertArrayEquals(expected2, MyArrays.removeRepeated(numbersRepeatedValues));
	}

}
