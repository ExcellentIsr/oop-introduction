package telran.baseCipher;

public class BaseCipher {
	private int length;
	private String key = "";
	public byte[] helper;

	public BaseCipher(int length) {
		this.setLength(length);
		this.key = generateKeys(getLength());
	}

	public String cipher(int number) {
		String res = "";
		char[] keyArray = getKey().toCharArray();
		int lenght = getLength();

		while (number >= lenght) {
			res = keyArray[number - ((int) (number / lenght) * lenght)] + res;
			number /= lenght;
		}

		return keyArray[number] + res;
	}

	public int decipher(String cipher) {
		int res = 0;
		String key = getKey();
		int cipherLength = cipher.length();

		for (int i = 0; i < cipherLength; i++) {
			if (cipher.charAt(i) == key.charAt(helper[(int) cipher.charAt(i) - 33])) {
				res += helper[(int) cipher.charAt(i) - 33] * Math.pow(getLength(), cipherLength - 1 - i);
			} else {
				res = -1;
				break;
			}
		}

		return res;
	}

	private String generateKeys(int length) {
		String res = "";
		int min = 33;
		int max = 126;
		helper = new byte[max - min + 1];
		for (int i = 0; i < helper.length; i++) {
			helper[i] = -1;
		}
		int number = 0;

		for (int i = 0; i < length; i++) {
			do {
				number = (int) (Math.random() * (max - min + 1)) + min;
			} while (helper[number - min] > -1);
			helper[number - min] = (byte) i;
			res += (char) number;
		}

		return res;
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
