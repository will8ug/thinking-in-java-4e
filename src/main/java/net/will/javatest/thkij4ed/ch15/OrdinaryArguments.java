/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-22
 */
public class OrdinaryArguments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Base base = new Base();
		Derived derived = new Derived();
		DerivedSetter ds = new DerivedSetter();
		ds.set(derived);
		ds.set(base);   // compiles: overloaded, not overridden!
	}

}

class Base {}
class Derived extends Base {}

class OrdinarySetter {
	void set(Base base) {
		System.out.println("OrdinarySetter.set(Base)");
	}
}

class DerivedSetter extends OrdinarySetter {
	void set(Derived derived) {
		System.out.println("DerivedSetter.set(Derived)");
	}
}
