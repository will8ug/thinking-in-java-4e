/**
 * 
 */
package net.will.javatest.thkij4ed.ch13;

/**
 * 
 * @author Will
 * @version 2012-10-20
 */
public class Replacing {
	static String s = Splitting.knights;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(s.replaceFirst("f\\w+", "located"));
		System.out.println(s.replaceAll("shrubbery|tree|herring", "banana"));
	}

}
