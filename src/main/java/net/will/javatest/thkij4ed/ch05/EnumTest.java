/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * @author Will
 * @version 2011-9-10
 */
public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (Spiciness s: Spiciness.values()) {
			System.out.println(s + ", ordinal " + s.ordinal());
		}
	}

}

enum Spiciness {
	NOT, MILD, MEDIUM, HOT, FLAMING
}
