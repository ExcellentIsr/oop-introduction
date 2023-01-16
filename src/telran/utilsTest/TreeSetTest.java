package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.*;

class TreeSetTest extends SetTest{
	
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
	}

}
