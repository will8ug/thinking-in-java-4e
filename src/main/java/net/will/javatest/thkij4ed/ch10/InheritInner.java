/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>Inheriting an inner class.</p>
 * 
 * <p>
 * You can see that <code>InheritInner</code> is extending only the
 * inner class, not the outer one. But when it comes time to create
 * a constructor, the default one is no good, and you can't just
 * pass a reference to an enclosing object. In addition, you must
 * use the syntax
 * <pre>enclosingClassReference.super();</pre>
 * inside the constructor. This provides the necessary reference,
 * and the program will then compile.
 * </p>
 * 
 * @author Will
 * @version 2012-10-15
 */
public class InheritInner extends WithInner.Inner {
	// InheritInner() {}  // won't compile
	InheritInner(WithInner wi) {
		wi.super();
	}

	public static void main(String[] args) {
		WithInner wi = new WithInner();
		InheritInner ii = new InheritInner(wi);
		System.out.println(ii);
	}

}

class WithInner {
	class Inner {}
}