/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * @author Will
 * @version 2011-10-20
 */
public class ConstructorTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Y(5);
	}

}

class X {
	int i;
	public X(int i) {
		this.i = i;
	}
}

class Y extends X {
	// compile error
//	public Y() {
//		// NOP
//	}
	
	public Y(int i) {
		super(i);
	}
}