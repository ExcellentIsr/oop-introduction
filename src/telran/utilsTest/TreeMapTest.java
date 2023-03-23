package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.TreeMap;

public class TreeMapTest extends MapTest {
	@BeforeEach
	@Override
	void setUp() throws Exception{
		map = new TreeMap<> ();
		super.setUp();
	}
}