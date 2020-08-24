package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length() > s2.length()) {
			return s1;
		}
		else if(s2.length() > s1.length()) {
			return s2;
		}
		else {
			return "equal";
		}
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	String newString;
	
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			String newString = s.replace(" ", "_");
			return newString;
		}
		else {
			return s;
		}
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	
	public static String lineLeader(String s1, String s2, String s3) {
		s1 = s1.trim();
		s2 = s2.trim();
		s3 = s3.trim();
		String[] s1Array = s1.split(" ");
		String[] s2Array = s2.split(" ");
		String[] s3Array = s3.split(" ");
		if(s1Array[1].compareToIgnoreCase(s2Array[1]) <= 0 && s1Array[1].compareToIgnoreCase(s3Array[1]) <= 0) {
			return s1;
		}
		if(s2Array[1].compareToIgnoreCase(s1Array[1]) <= 0 && s2Array[1].compareToIgnoreCase(s3Array[1]) <= 0) {
			return s2;
		}
		if(s3Array[1].compareToIgnoreCase(s1Array[1]) <= 0 && s3Array[1].compareToIgnoreCase(s2Array[1]) <= 0) {
			return s3;
		}
		else {
			return null;
		}
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			if(Character.isDigit(s.charAt(i))) {
				sum = sum + Integer.parseInt("" + s.charAt(i));
			}
		}
		return sum;
	}
	
	
	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int sum = 0;
		for(int i = 0; i < s.length();i++) {
			if(i + substring.length() <= s.length()) {
				if(s.substring(i,i + substring.length()).equals(substring)) {
					sum++;
				}
			}
		}
		return sum;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		String keyS = "" + key;
		
		return Utilities.encrypt(s.getBytes(), keyS.getBytes()[0]);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		String keyS = "" + key;
		return Utilities.decrypt(s, keyS.getBytes()[0]);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int sum = 0;
		for(int i = 0; i < s.length();i++) {
			if(i + substring.length() <= s.length()) {
				if(s.substring(i,i + substring.length()).equals(substring)) {
					sum++;
				}
			}
		}
		return sum;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		boolean found = false;
		int first = 0;
		int last = 0;
		int sum = 0;
		for(int i = 0; i < s.length();i++) {
			if(i + substring.length() <= s.length()) {
				if(s.substring(i,i + substring.length()).equals(substring)) {
					first = i;
					break;
				}
			}
		}
		for(int i = s.length() - substring.length(); i > 0;i--) {
			if(s.substring(i,i + substring.length()).equals(substring)) {
				last = i;
				break;
			}
		}
		return Math.abs(first - last) - substring.length();
	}


	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		
		s = s.replaceAll(",", " ");
		s = s.replaceAll(".", " ");
		s = s.replaceAll(":", " ");
		s = s.trim();
		
		String[] sReverse = new String[s.length()];
		
		int counter = 0;
		
		for(int i = s.length() - 1; i >= 0;i--) {
			sReverse[counter] = "" + s.charAt(i);
			counter++;
		}
		
		System.out.println(sReverse.toString());
		System.out.println(s);
		if(sReverse.toString().equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
	
}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
