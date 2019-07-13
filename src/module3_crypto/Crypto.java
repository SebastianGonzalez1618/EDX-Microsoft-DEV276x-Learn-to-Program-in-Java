package module3_crypto;

import java.util.Scanner;


public class Crypto {

	public static final String unshiftedAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main (String[] args) {

		Scanner input = new Scanner(System.in);
		Scanner inputLine = new Scanner(System.in);
		String originalText = "";
		int shift = 0;
		int size = 0;
		
		System.out.print("Enter a text to encrypt: ");
		originalText = inputLine.nextLine();
		System.out.print("Enter shift value: ");
		shift = input.nextInt();
		System.out.print("Enter group size: ");
		size = input.nextInt();
		System.out.println("The encrypted text is: " + encryptString(originalText, shift, size));
		
		System.out.println();
		
		System.out.print("Enter an encrypted text to decrypt: ");
		originalText = inputLine.nextLine();
		System.out.print("Enter the shift value used to encrypt the text: ");
		shift = input.nextInt();
		System.out.println("The (normalized) decrypted text is: " + decryptString(originalText, shift));
		
		//input.close();
		//inputLine.close();
	}

	
	
	/*
	 * **************************************************
	  Part 1 - Normalize Text
	  
	  The first thing we will do is normalize our input
	  message so that it�s easier to work with.
	  
	  Write a method called normalizeText() which does the following:
	  
	  1. Removes all the spaces from your text 2. Remove any punctuation (. , : ; �
	  � ! ? ( ) ) 3. Turn all lower-case letters into upper-case letters 4. Return
	  the result. The call
	  
	  normalizeText("This is some \"really\" great. (Text)!?") should return
	  
	  �THISISSOMEREALLYGREATTEXT�
	 */
	

	public static String normalizeText(String originalText) {
			String normalizedText = "";
			String currentLetter = "";
			int counter = 0;
						
			for(counter = 0;counter < originalText.length();++counter) {
				currentLetter = originalText.substring(counter,counter + 1).toUpperCase();
				if (!" .,:;���!�?()\"".contains(currentLetter)) {
					normalizedText += currentLetter;
				}
			}
			
			return normalizedText;
	}

	
	/*
	 * ( The next section is somehow mentioned in the video explanation
	 * of the assignment, but not included in the written explanation.
	 * Someone somehow got it and shared it in the lesson's discussion )
	 * 
	 * Our next method will be called obify. This will take in the normalized text
	 * and insert a capital O and capital B in front of every vowel, including y,
	 * and return the obify text.
	 */
	
	
	
	/*
	 * **************************************************
	  Part 2 - Caesar Cipher
	  
	  Next we�ll be writing a Caesar Cipher. The Caesar
	  cipher is just about the simplest encryption algorithm out there. A Caesar
	  encription "shifts" each individual character forward by a certain number or
	  "key". Each letter in the alphabet is shifted to the letter in the alphabet
	  that is "key" places past the original letter. With a shift value of +1, the
	  string �ILIKEZOOS� would be rendered as �JMJLFAPPT.�
	  
	  Write a method called caesarify that takes two parameters. The first argument
	  is a string you want to encrypt, and the second is an integer that contains
	  the shift value or "key". The function should return a string, which is the
	  input string encrypted with the Caesar cypher using the shift value passed in
	  its second argument. You may assume that the input string is normalized.
	  
	  Note that the alphabet �wraps around�, so with a shift value of +1 the �Z� in
	  ZOOS became an A. You can also have negative shift values, which cause the
	  alphabet to previous letters. With a -1 shift, the string �ILIKEAPPLES� would
	  turn into �HKHJDZOOKDR.� We will provide you with a function called
	  shiftAlphabet. This function takes one argument, an integer to specify the
	  shift value, and returns a string, which is the uppercase alphabet shifted by
	  the shift value. So if you call shiftAlphabet(2), you will get back the
	  following string: �CDEFGHIJKLMNOPQRSTUVWXYZAB�
	  
	  Here is the implementation for shiftAlphabet, which you can just paste into
	  your java file:
	  
		public static String shiftAlphabet(int shift) {
		    int start = 0;
		    if (shift < 0) {
		        start = (int) 'Z' + shift + 1;
		    } else {
		        start = 'A' + shift;
		    }
		    String result = "";
		    char currChar = (char) start;
		    for(; currChar <= 'Z'; ++currChar) {
		        result = result + currChar;
		    }
		    if(result.length() < 26) {
		        for(currChar = 'A'; result.length() < 26; ++currChar) {
		            result = result + currChar;
		        }
		    }
		    return result;
		}
	 */
	
	public static String shiftAlphabet (int shift) {
		
		String shiftedAlphabet = "";
		int alphabetLength = unshiftedAlphabet.length();
		int start = 0;
		for (int count = 0; count < alphabetLength; count++) {
			if (shift >= 0) {
				start = (count + shift) % alphabetLength;
			} else {
				start = (alphabetLength + count + ( shift % alphabetLength) ) % alphabetLength;
			}
			shiftedAlphabet += unshiftedAlphabet.substring(start, start + 1);
		}
		return shiftedAlphabet;
	}
	

	public static String caesarify (String normalizedText, int shift) {
		String caesarifiedText = "";
		String currentLetterUnshifted = "";
		String currentLetterShifted = "";
		String shiftedAlphabet = "";
		int unshiftedLetterIndex = 0;
		
		shiftedAlphabet = shiftAlphabet(shift);
		
		/*Unshifted: ABC	0 = A;	1 = B;	2 = C		24 = Y;		25 = Z
		 * Shift:     1		
		 * Shifted:  BCD	0 = B;	1 = C;	2 = D		24 = Z:		25 = A
		 * 
		 * NormText: ABY	0 = A;	1 = B;	2 = Y		
		 * CaeText:  BCZ	0 = B;	1 = C;	2 = Z
		 * 
		 */
		
		for (int count = 0; count < normalizedText.length(); count++) {
			currentLetterUnshifted = normalizedText.substring(count, count + 1);
			unshiftedLetterIndex = unshiftedAlphabet.indexOf(currentLetterUnshifted);
			
			currentLetterShifted = shiftedAlphabet.substring(unshiftedLetterIndex, unshiftedLetterIndex + 1);
			
			caesarifiedText += currentLetterShifted;
		}
		
		return caesarifiedText;
	}
		
	
	
	/*
	 * **************************************************
	  Part 3 - Codegroups Traditionally, encrypted messages are broken into
	  equal-length chunks, separated by spaces and called �code groups.�
	  
	  Write a method called groupify which takes two parameters. The first
	  parameter is the string that you want to break into groups. The second
	  argument is the number of letters per group. The function will return a
	  string, which consists of the input string broken into groups with the number
	  of letters specified by the second argument. If there aren�t enough letters
	  in the input string to fill out all the groups, you should �pad� the final
	  group with x�s. So groupify(�HITHERE�, 2) would return �HI TH ER Ex�.
	  
	  You may assume that the input string is normalized. Note that we use
	  lower-case �x� here because it is not a member of the (upper-case) alphabet
	  we�re working with. If we used upper-case �X� here we would not be able to
	  distinguish between an X that was part of the code and a padding X.
	 */
	
	public static String groupify (String caesarifiedText, int groupSize) {
		String groupifiedText = "";
		int count = 0;
		String currentLetter = "";
		int padding = 0;
		
		
		for (count = 0; count < caesarifiedText.length(); count++) {
			currentLetter = caesarifiedText.substring(count, count + 1);
			groupifiedText += currentLetter;
			
			if ( ( (count + 1) % groupSize == 0 ) && ( (count + 1) < caesarifiedText.length() ) ) {
				groupifiedText += " ";
			}
		}
		
		if (groupSize < caesarifiedText.length() && ( (caesarifiedText.length() % groupSize) != 0 ) ) {
			padding = groupSize - ( caesarifiedText.length() % groupSize);
		} else {
			padding = groupSize - caesarifiedText.length();
		}
		
		for (count = 0; count < padding; count++) {
			groupifiedText += "x";
		}
		
		return groupifiedText;
	}
	
	/*
	 * **************************************************
	  Part 4 - Putting it all together Write a function called encryptString which
	  takes three parameters: a string to be encrypted, an integer shift value, and
	  a code group size. Your method should return a string which is its cyphertext
	  equivalent. Your function should do the following:
	  
	  Call normalizeText on the input string. Call obify to obfuscate the
	  normalized text. Call caesarify to encrypt the obfuscated text. Call groupify
	  to break the cyphertext into groups of size letters. Return the result
	 */
	
	public static String encryptString (String originalText, int shift, int groupSize) {
		String encryptedString = "";
		
		encryptedString = normalizeText(originalText);
		encryptedString = caesarify(encryptedString, shift);
		encryptedString = groupify(encryptedString, groupSize);
		
		return encryptedString;
	}
	
	/*
	 * **************************************************
	  Part 5 - Hacker Problem - Decrypt This part is not required for course
	  credit.
	  
	  Write a method called ungroupify which takes one parameter, a string
	  containing space-separated groups, and returns the string without any spaces.
	  So if you call ungroupify(�THI SIS ARE ALL YGR EAT SEN TEN CEx�) you will
	  return �THISISAREALLYGREATSENTENCE�
	  
	  Now write a function called decryptString which takes three parameters: a
	  string to be decrypted and the integer shift value used to encrypt the
	  string, and returns a string which contains the (normalized) plaintext. You
	  can assume the string was encrypted by a call to encryptString().
	  
	  So if you were to call
	  
	  String cyphertext = encryptString(�Who will win the election?�, 5, 3);
	  String plaintext = decryptString(cyphertext, 5);
	   � then you�ll get back the normalized input string �WHOWILLWINTHEELECTION�.
	 */

	
	public static String ungroupify(String encryptedText) {
		String ungroupifiedText = "";
		String currentLetter = "";
		int counter = 0;
					
		for(counter = 0;counter < encryptedText.length();++counter) {
			currentLetter = encryptedText.substring(counter,counter + 1);
			if (!" x".contains(currentLetter)) {
				ungroupifiedText += currentLetter;
			}
		}
		return ungroupifiedText;
}
	
	public static String decryptString ( String originalText, int shift) {
		String decryptedText = "";
		
		decryptedText = ungroupify(originalText);
		decryptedText = caesarify(decryptedText, - shift);
		
		return decryptedText;
	}
	
}
