/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * If you define any constructors (with or without arguments) explicitly, the compiler
 * will not synthesize another one for you.
 * 
 * @author Will
 * @version 2011-9-5
 */
public class ConstructorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree t = new Tree(10);
//		Tree t0 = new Tree();      // compile ERROR
		System.out.println(t.getHValue());
	}

}

class Tree {
	private int h;
	public Tree(int h) {
		this.h = h;
	}
	public int getHValue() {
		return this.h;
	}
}