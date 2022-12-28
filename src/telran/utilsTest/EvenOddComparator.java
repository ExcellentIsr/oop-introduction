package telran.utilsTest;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res = 0;

		if (isEven(o1, 2)) {
			res = (isEven(o2, 2)) ?  Integer.compare(o1, o2) : -1;
		} else {
			res = (isEven(o2, 2)) ? 1 : Integer.compare(o2, o1);
		}
		return res;
	}
	
	boolean isEven(Integer number, int divider) {
		return number % divider == 0;
	}

}
