package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.*;

class ArrayListTest extends ListTest {
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}
}