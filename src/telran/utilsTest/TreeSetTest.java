package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.*;

public class TreeSetTest extends SortedTest {

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<Integer>();
		super.setUp();
	}

}
