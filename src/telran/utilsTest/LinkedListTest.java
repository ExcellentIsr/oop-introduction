package telran.utilsTest;


import org.junit.jupiter.api.BeforeEach;
import telran.utils.*;

public class LinkedListTest extends ListTest {
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}
}
