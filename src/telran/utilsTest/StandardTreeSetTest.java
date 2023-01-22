package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;
import telran.utils.*;

public class StandardTreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new StandardTreeSet<Integer>();
		super.setUp();
	}
}