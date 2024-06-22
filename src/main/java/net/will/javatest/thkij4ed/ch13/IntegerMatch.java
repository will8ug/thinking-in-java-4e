/**
 * 
 */
package net.will.javatest.thkij4ed.ch13;

/**
 * @author Will
 * @version 2011-10-12
 */
public class IntegerMatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("5678".matches("-?\\d+"));
		System.out.println("+911".matches("-?\\d+"));
		System.out.println("+911".matches("(-|\\+)?\\d+"));
		System.out.println("+911".matches("[-\\+]?\\d+"));
		System.out.println("+911".matches("[-\\+]?[\\d[0-9]\\d]+"));
		System.out.println("blue".matches("blue|berry"));
		System.out.println("berry".matches("blue|berry"));
		System.out.println("berry ".matches("blue|berry"));
	}

}
