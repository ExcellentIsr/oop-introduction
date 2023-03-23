package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.HashMap;

public class HashMapTest extends MapTest {
	@BeforeEach
	@Override
	void setUp() throws Exception{
		map = new HashMap<> ();
		super.setUp();
	}
}
