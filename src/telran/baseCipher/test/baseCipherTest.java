package telran.baseCipher.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import telran.baseCipher.BaseCipher;

public class baseCipherTest {

	@Test
	void CipherTest() {
		int numeral = 16;
		int number = 1023;

		BaseCipher example = new BaseCipher(numeral);
		System.out.println(example.getKey());

		System.out.println(example.cipher(number));
		System.out.println(example.decipher(example.cipher(number)));

		numeral = 8;
		number = 63;
		example.setLength(numeral);

		System.out.println(example.cipher(number));
		System.out.println(example.decipher(example.cipher(number)));

		//System.out.println(example.decipher("JV"));
		//assertEquals(-1, example.decipher("_ z.todb"));
		
		for(int i = 0; i < example.helper.length; i++) {
			System.out.print(example.helper[i] + " ");
		}
		
	}
}
