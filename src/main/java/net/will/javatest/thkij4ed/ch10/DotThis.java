/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Qualifying access to the outer-class object.
 *
 * @author Will
 * @version 2012-8-29
 */
public class DotThis {
	void f() { System.out.println("DotThis.f()"); }
	public class Inner {
		public DotThis outer() {
			// a plain "this" would be Inner's "this"
			System.out.println(this);
			System.out.println(DotThis.this);
			return DotThis.this;
		}
	}
	public Inner inner() { return new Inner(); }
	public static void main(String[] args) {
		DotThis dt = new DotThis();
		
		// section 01:
//		DotThis.Inner test00 = new DotThis.Inner();   // error code
		DotThis.Inner test00 = dt.new Inner();
		test00.outer().f();

		// section 02:
		Inner test01 = dt.inner();  // no "DotThis." is also permitted
		test01.outer().f();

		// section 03:
		DotThis.Inner dti = dt.inner();
		dti.outer().f();
	}
}
