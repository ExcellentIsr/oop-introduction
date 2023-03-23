package telran.utilsTest;

import org.junit.jupiter.api.BeforeEach;

import telran.utils.LinkedHashSet;

public class LinkedHashSetTest extends SetTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedHashSet<>();
		super.setUp();
	}
}
