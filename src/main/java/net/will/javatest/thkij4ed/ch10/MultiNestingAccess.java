/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>
 * Nested classes can access all members of all levels of the
 * classes they are nested within.
 * </p>
 * <p>
 * You can see that in <code>MNAAB</code>, the methods g() and
 * f() are callable without any qualification (despite the fact
 * that they are <code>private</code>). This example also
 * demonstrates the syntax necessary to create objects of
 * multiply nested inner classes when you create the objects
 * in a different class. The "<code>.new</code>" syntax produces
 * the correct scope, so you do not have to qualify the class
 * name in the constructor call.
 * </p>
 *
 * @author Will
 * @version 2012-9-5
 */
public class MultiNestingAccess {

	public static void main(String[] args) {
		Mna mna = new Mna();
		Mna.A mnaa = mna.new A();
		Mna.A.B mnaab = mnaa.new B();
		mnaab.h();
	}

}

class Mna {
	private void f() {}
	class A {
		private void g() {}
		public class B {
			void h() {
				g();
				f();
			}
		}
	}
}
