package telran.memory;

public class MemoryOperation {

	public static int getMaxAvaibleMemory() {
		int res = Integer.MAX_VALUE;
		int low = 0;
		int high = Integer.MAX_VALUE;
		int middle = high / 2;
		
		boolean running = true;
		
		while (low <= high) {
			try {
				byte[] ar = new byte[middle];
				res = middle;
				low = middle + 1;
			} catch (Throwable e) {
				high = middle - 1;
			}
			middle = low + (high - low) / 2;
		}
		
		return res;
	}
}
