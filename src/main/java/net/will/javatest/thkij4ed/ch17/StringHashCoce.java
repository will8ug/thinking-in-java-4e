/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

/**
 * 
 * @author Will
 * @version 2012-10-18
 */
public class StringHashCoce {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] hellos = "Hello Hello".split(" ");
		System.out.println(hellos[0].hashCode());
		System.out.println(hellos[1].hashCode());
	}

}
