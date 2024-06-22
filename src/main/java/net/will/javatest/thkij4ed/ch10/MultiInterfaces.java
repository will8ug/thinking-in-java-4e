/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Two ways that a class can implement multiple interfaces.
 *
 * @author Will
 * @version 2012-9-6
 */
public class MultiInterfaces {
	static void takesA(A a) {}
	static void takesB(B b) {}

	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		takesA(x);
		takesA(y);
		takesB(x);
		takesB(y.makeB());
	}

}

interface A {}
interface B {}

class X implements A, B {}

class Y implements A {
	B makeB() {
		// anonymous inner class:
		return new B() {};
	}
}
