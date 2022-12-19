package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryOperationTest {
	byte ar[];
	
	@Test
	void maxMemoryTest() {
		int maxMemory = MemoryOperation.getMaxAvaibleMemory();
		ar = new byte[maxMemory];
		ar = null;
		System.out.println(maxMemory/1024/1024);
		
		boolean flException = false;
		try {
			ar = new byte[maxMemory + 1];
		}catch(Throwable e) {
			flException = true;
		}
		assertTrue(flException);
	}
}
