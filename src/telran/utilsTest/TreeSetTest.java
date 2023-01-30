package telran.utilsTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.*;

public class TreeSetTest extends SortedTest {
	TreeSet<Integer> tree;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<Integer>();
		super.setUp();
		tree = (TreeSet<Integer>) collection;
	}

	@Test
	void displayRotatedTest() {
		tree.displayTreeRotated();
	}

	@Test
	void heightTreeTest() {
		assertEquals(8, tree.height());
	}

	@Test
	void wigthTreeTest() {
		assertEquals(10, tree.width());
	}

	@Test
	void inversionTest() {
		tree.inversion();
		for (Integer num : tree) {
			System.out.print(num + " ");
		}
		Integer expected[] = { 100, 50, 40, 30, 25, 20, 15, 10, 7, 5, 3, 0, -5, -9, -10, -11, -12, -13, -14, -15, -17,
				-18 };
		Integer actual[] = new Integer[expected.length];

		int index = 0;
		for (Integer num : tree) {
			actual[index++] = num;
		}

		assertArrayEquals(expected, actual);
		assertTrue(tree.contains(100));
	}

	@Override
	protected Sorted<Integer> getSortedCollection() {
		return new TreeSet<>();
	}

	@Test
	void balanceTest() {
		tree.balance();
		assertEquals(5, tree.height());
		assertEquals(8, tree.width());
		System.out.println("*".repeat(10) + " balanced tree " + "*".repeat(10));
		tree.displayTreeRotated();
	}
}
