/**
 * 
 */
package net.will.javatest.thkij4ed.ch07;

/**
 * You can perform assignments to finals either with an expression at the point
 * of definition of the field or in every constructor, rather than in the instance
 * initialization clause.
 * 
 * @author Will
 * @version 2011-9-12
 */
public class BlankFinalTest {
	private int n;
	private final int i = 0;  // initialized final
	private final int j;      // blank final
	private final Poppet p;   // blank final reference
	private static final int k;  // test STATIC blank final
	
	// static clause
	static {
		k = 10;
	}
	
	// (non-static) instance initialization
	{
		n = 10;
//		i = 10;               // illegal
	}
	
	// blank finals MUST be initialzed in the constructor
	public BlankFinalTest() {
//		i = 2;                // illegal to initialize a not blank final field.
		j = 1;                // initialize blank final
		p = new Poppet(1);    // initialize blank final reference
	}
	
	public BlankFinalTest(int x) {
		j = x;                // initialize blank final
		p = new Poppet(x);    // initialize blank final reference
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BlankFinalTest();
		new BlankFinalTest(47);
	}
	
	public String toString() {
		return n + i + j + p.toString() + k;
	}

}

class Poppet {
	private int i;
	Poppet(int ii) {i = ii;}
	public String toString() {
		return String.valueOf(i);
	}
}