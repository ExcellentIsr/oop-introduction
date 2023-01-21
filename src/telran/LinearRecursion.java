package telran;

public class LinearRecursion {
	static public long factorial(int n) {
		long res = 0;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2) {
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}

	static public int power(int a, int b) {
		// your code cannot use cycles and *, / operators
		int res = 1;
		if (b < 0) {
			throw new IllegalArgumentException();
		} 
		if (b > 0){
			res = powerStep(a, power(Math.abs(a), b - 1));
		}
		return res;
	}

	private static int powerStep(int a, int step) {
		int res = 0;
		if (step > 0) {
			res = a + powerStep(a, step - 1);
		}
		return res;
	}

	static public long sum(int ar[]) {
		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}

	public static long square(int x) {
		// no cycles
		// no * , / operators
		// no additional functions
		// no static fields
		long res = 0;
		if(x < 0) {
			x = Math.abs(x);
		} 
		if (x > 0){
			res = x + (x - 1) + square(x - 1) ;
		}
		return res;
	}

	public static boolean isSubstring(String string, String substr) {
		boolean res = false;
		if (substr.length() == 0) {
			throw new IllegalArgumentException();
		} else {
			if (string.length() >= substr.length()) {
				res = string.substring(0, substr.length()).equals(substr) ? true
						: isSubstring(string.substring(1), substr);
			}
		}
		return res;
	}

	public static void reverse(int ar[]) {
		// no cycles
		// no static fields
		reverse(0, ar.length - 1, ar);
	}

	private static void reverse(int firstIndex, int lastIndex, int[] ar) {
		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}
	}

	private static void swap(int[] ar, int firstIndex, int lastIndex) {
		int tmp = ar[firstIndex];
		ar[firstIndex] = ar[lastIndex];
		ar[lastIndex] = tmp;
	}
}