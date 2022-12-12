package telran.baseCipher;

public class BaseCipher {
	private int length;
	private String key = "";

	private int min = 33;
	private int max = 126;
	public int[] helper = new int[max - min + 1];

	public BaseCipher(int length) {
		this.setLength(length);
		this.key = generateKeys(this.length);
	}

	public String cipher(int number) {
		String res = "";
		int lenght = getLength();

		while (number >= lenght) {
			res = key.charAt(number % length) + res;
			number /= lenght;
		}

		return key.charAt(number) + res;
	}

	public int decipher(String cipher) {
		int res = 0;
		int cipherLength = cipher.length();

		for (int i = 0; i < cipherLength; i++) {
			int currentIndex = (int) cipher.charAt(i);
			int indexInCipher = (currentIndex < min || currentIndex > max) ? -1 : helper[currentIndex - min];

			if (indexInCipher > -1) {
				res += indexInCipher * Math.pow(getLength(), cipherLength - 1 - i);
			} else {
				res = -1;
				break;
			}
		}

		return res;
	}

	private String generateKeys(int length) {
		String res = "";
		for (int i = 0; i < helper.length; i++) {
			helper[i] = -1;
		}

		for (int i = 0; i < length; i++) {
			res += getUniqueRandomChar(i);
		}
		return res;
	}

	private char getUniqueRandomChar(int i) {
		int rand;
		do {
			rand = (int) (min + Math.random() * (max - min + 1));
		} while (helper[rand - min] > -1);

		helper[rand - min] = i;

		return (char) rand;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		if (length < 2) {
			this.length = 2;
		} else if (length > 94) {
			this.length = 94;
		} else {
			this.length = length;
		}

	}

	public String getKey() {
		return key;
	}
}
