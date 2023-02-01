package telran.utilsTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.Sorted;

public abstract class SortedTest extends SetTest {
	protected static final int N_ELEMNTS = 100000;
	protected static final int N_RUNS = 10000;
	private Random gen = new Random();
	Sorted<Integer> sorted;

	@Override
	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		sorted = (Sorted<Integer>) collection;
	}

	@Override
	@Test
	void testIterator() {
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		Integer actual[] = new Integer[expected.length];
		int index = 0;
		for (Integer num : sorted) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
	}

	// { 10, 30, 0, 50, 20, 5, 100, 15, 40, 25, 3, -10, -9, 7, -18, -5, -15, -12,
	// -11, -17, -13, -14 };
	@Test
	void floorTest() {
		assertEquals((Integer) 100, sorted.floor(100));
		assertNull(sorted.floor(-20));
		assertEquals((Integer) 15, sorted.floor(18));
		assertEquals((Integer) 100, sorted.floor(115));
	}

	@Test
	void ceilingTest() {
		assertEquals((Integer) 100, sorted.ceiling(100));
		assertNull(sorted.ceiling(200));
		assertEquals((Integer) 15, sorted.ceiling(13));
		assertEquals((Integer) 0, sorted.ceiling(0));
		assertEquals((Integer) (-18), sorted.ceiling(-20));
	}

	@Test
	void firstTest() {
		assertEquals((Integer) (-18), sorted.first());
	}

	@Test
	void lastTest() {
		assertEquals((Integer) 100, sorted.last());
	}

	protected void runPerformanceTest(Sorted<Integer> sorted) {

		for (int i = 0; i < N_RUNS; i++) {
			sorted.floor(gen.nextInt());
		}
	}

	protected abstract Sorted<Integer> getSortedCollection();

	@Test
	void performanceTestRandomAdding() {
		Sorted<Integer> sorted = getSortedCollection();
		IntStream.range(0, N_ELEMNTS).forEach(x -> sorted.add(gen.nextInt()));
		runPerformanceTest(sorted);
	}
}