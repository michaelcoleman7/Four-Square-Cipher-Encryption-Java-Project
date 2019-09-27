package ie.gmit.sw;

public class Encrypter {

	private FourSquareCipher fsc;

	// return encrypted text - method constant time, but calling on method which is n log n
	public String encrypt(String text, FourSquareCipher fsc) {
		this.fsc = fsc;
		String encryptedText = getEncText(text);
		return encryptedText;
	}

	//Method to get the encrypted text - (n log n) as increases in size based on size of string
	private String getEncText(String str) {
		StringBuilder sb = new StringBuilder();

		char charOne = ' ';
		char charTwo = ' ';
		boolean space1 = false, space2 = false, newLine1 = false, newLine2 = false;
		String bigram;
		int index;

		for (index = 0; index < str.length();) {
			try {
				charOne = str.charAt(index);
				index++;
			} catch (Exception e) {
				charOne = 'x';
			}

			// while character 1 is a space
			while (charOne == ' ') {
				try {
					charOne = str.charAt(index);
					index++;
					space1 = true;
				} catch (Exception e) {
					charOne = 'x';
				}
			}

			// while character 1 is a new line
			while (charOne == '~') {
				try {
					charOne = str.charAt(index);
					index++;
					newLine1 = true;
				} catch (Exception e) {
					charOne = 'x';
				}
			}

			try {
				charTwo = str.charAt(index);
				index++;
			} catch (Exception e) {
				charTwo = 'x';
			}

			// while character 2 is a space
			while (charTwo == ' ') {
				try {
					charTwo = str.charAt(index);
					index++;
					space2 = true;
				} catch (Exception e) {
					charTwo = 'x';
				}
			}

			// while character 2 is a new line
			while (charTwo == '~') {
				try {
					charTwo = str.charAt(index);
					index++;
					newLine2 = true;
				} catch (Exception e) {
					charTwo = 'z';// uneven length
				}
			}
			// create bigram
			bigram = charOne + "" + charTwo;

			// encrypt bigram
			String encBigram = encryptBigram(bigram);

			// adding spaces and new lines to stringbuilder
			if (space1) {
				sb.append(' ');
			}
			if (newLine1) {
				sb.append('~');
			}

			if (charOne == 'x') {
				// ignore Character
			} else
				// add 1st character to stringbuilder
				sb.append(encBigram.charAt(0));

			if (space2) {
				sb.append(' ');
			}
			if (newLine2) {
				sb.append('~');
			}

			if (charTwo == 'x') {
				// ignore Character
			} else
				// add 2nd character to stringbuilder
				sb.append(encBigram.charAt(1));

			// reset space and new line values
			space1 = false;
			space2 = false;
			newLine1 = false;
			newLine2 = false;
		}

		return sb.toString();
	}

	// Method to encrypt bigrams from FourSquareCipher class  - method constant time, but calling on method which is o(n)
	private String encryptBigram(String bigram) {
		String encryptedBigram;
		encryptedBigram = fsc.encrypt(bigram);
		return encryptedBigram;
	}

}
