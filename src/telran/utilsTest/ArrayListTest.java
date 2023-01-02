package telran.utilsTest;

import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.utils.*;

class ArrayListTest extends ListTest {
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}
}