/**
 * 
 */
package net.will.javatest.thkij4ed.ch13;

import java.util.Arrays;

/**
 * 
 * @author Will
 * @version 2012-10-20
 */
public class Splitting {
	public static String knights = "Then, when you have found the shrubbery, you must "
			+ "cut down the mightiest tree in the forest... "
			+ "with... a herring!";

	public static void split(String regex) {
		System.out.println(Arrays.toString(knights.split(regex)));
	}

	public static void main(String[] args) {
		split(" "); // Doesn't have to contain regex chars
		split("\\W+"); // Non-word characters
		split("n\\W+"); // 'n' followed by non-word characters
	}
}
