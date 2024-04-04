package org.jlobato.gpro.utils;


/**
 * The Class RomanNumeral.
 *
 * @author jmplobato
 */
public class RomanNumeral {

	/**
	 * To roman.
	 *
	 * @param number the number
	 * @return the string
	 */
	public static String toRoman(int number) {
		StringBuilder br = new StringBuilder("");
		while (number != 0) {
			while (number >= 1000) {
				br.append("M");
				number -= 1000;
			}
			while (number >= 900) {
				br.append("CM");
				number -= 900;
			}
			while (number >= 500) {
				br.append("D");
				number -= 500;
			}
			while (number >= 400) {
				br.append("CD");
				number -= 400;
			}
			while (number >= 100) {
				br.append("C");
				number -= 100;
			}
			while (number >= 90) {
				br.append("XC");
				number -= 90;
			}
			while (number >= 50) {
				br.append("L");
				number -= 50;
			}
			while (number >= 40) {
				br.append("XL");
				number -= 40;
			}
			while (number >= 10) {
				br.append("X");
				number -= 10;
			}
			while (number >= 9) {
				br.append("IX");
				number -= 9;
			}
			while (number >= 5) {
				br.append("V");
				number -= 5;
			}
			while (number >= 4) {
				br.append("IV");
				number -= 4;
			}
			while (number >= 1) {
				br.append("I");
				number -= 1;
			}
		}
		return br.toString();
	}
}
