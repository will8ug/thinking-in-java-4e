/**
 * 
 */
package net.will.javatest.thkij4ed.ch20;

/**
 * Java assertions normally have to be enabled with the -ea flag
 * on the java command line.
 *
 * @author Will
 * @version 2011-12-4
 */
public class AtUnitExample2 {
	public String methodOne() {
		return "this is methodOne";
	}
	
	public int methodTwo() {
		System.out.println("This is methodTwo");
		return 2;
	}
	
	public void assertExample() {
		assert methodOne().equals("this is methodOne"); // legal
	}
	
	public void assertFailureExample() {
		assert 1 == 2 : "What a surprise!";  // legal
	}

	public static void main(String[] args) {

	}

}
