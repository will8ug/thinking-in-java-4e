/**
 * 
 */
package net.will.javatest.thkij4ed.ch13;

/**
 * 
 * @author Will
 * @version 2012-10-21
 */
public class Rudolph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (String pattern : new String[] { "Rudolph", "[rR]udolph",
				"[rR][aeiou][a-z]ol.*", "R.*" })
			System.out.println("Rudolph".matches(pattern));
	}

}
