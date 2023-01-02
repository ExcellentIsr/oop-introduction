package telran.utilsTest;

import static org.junit.Assert.assertArrayEquals;
//import java.util.Iterator;
import org.junit.jupiter.api.Test;
import telran.utils.*;

public class RangeIteratorTest {
	Integer numbers[] = { 1, 2, 3, 4, 5 };

	@Test
	void test() {
		Range range = new Range(1, 6);
		ArrayList<Integer> list = new ArrayList<>();
//		Iterator<Integer> it = range.iterator();
//		
//		while(it.hasNext()) {
//			list.add(it.next());
//		}
		for (Integer num : range) {
			list.add(num);
		}
		assertArrayEquals(numbers, list.toArray(new Integer[0]));
	}

}