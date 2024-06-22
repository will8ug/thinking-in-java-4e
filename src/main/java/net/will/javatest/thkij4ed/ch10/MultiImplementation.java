/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * With concrete or abstract classes, inner classes are the only way
 * to produce the effect of "multiple implementation inheritance."
 *
 * @author Will
 * @version 2012-9-6
 */
public class MultiImplementation {
	static void takesD(D d) {}
	static void takesE(E d) {}

	public static void main(String[] args) {
		Z z = new Z();
		takesD(z);
		takesE(z.makeE());
	}

}

class D {}

abstract class E {}

class Z extends D {
	E makeE() { return new E() {}; }
}
