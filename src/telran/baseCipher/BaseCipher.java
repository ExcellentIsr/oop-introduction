package telran.baseCipher;

public class BaseCipher {
	private int length;
	private String key = "";

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
			;
			number /= lenght;
		}

		return res + keyArray[number];
	}

	public int decipher(String cipher) {
		int res = 0;
		if (cipher.matches(cipherIsTrue())) {
			String key = getKey();
			int cipherLength = cipher.length();

			for (int i = 0; i < cipherLength; i++) {
				boolean find = false;
				int j = 0;
				while (!find && j < key.length()) {
					if (key.charAt(j) == cipher.charAt(i)) {
						find = true;
						res += j * Math.pow(getLength(), cipherLength - 1 - i);
					} else {
						j++;
					}
				}
				if (!find) {
					res = -1;
					break;
				}
			}
		} else {
			res = -1;
		}
		return res;
	}

	private String cipherIsTrue() {
		return "[\\]\\[\\w\\!\\?\\\"\\#\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;\\<\\=\\>\\@\\\\^\\}\\{\\|\\~\\`]*";
	}

	private String generateKeys(int length) {
		String res = "";
		int min = 33;
		int max = 126;
		boolean[] helper = new boolean[max - min + 1];
		int number = 0;

		for (int i = 0; i < length; i++) {
			do {
				number = (int) (Math.random() * (max - min + 1)) + min;
			} while (helper[number - min]);
			helper[number - min] = true;
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
