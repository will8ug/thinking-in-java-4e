/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>
 * Creating a constructor for an anonymous inner class.
 * </p>
 * <p>
 * What if you need to perform some constructor-like activity? You can't
 * have a named constructor in an anonymous class (since there's no name!),
 * but with <em>instance initialization</em>, you can, in effect, create
 * a constructor for an anonymous inner class, like this example.
 * </p>
 * </p>
 * In this case, the variable <code>i</code> did nor have to be final.
 * While <code>i</code> is passed to the base constructor of the anonymous
 * class, it is never directly used <em>inside</em> the anonymous class.
 * <p>
 *
 * @author Will
 * @version 2012-9-3
 */
public class AnonymousConstructor {
	public static Base getBase(int i) {
		return new Base(i) {
			{ System.out.println("Inside instance initializer"); }
			@Override
			public void f() {
				System.out.println("In anonymous f()");
			}
		};
	}

	public static void main(String[] args) {
		Base base = getBase(47);
		base.f();
	}

}

abstract class Base {
	public Base(int i) {
		System.out.println("Base Constructor, i=" + i);
	}
	public abstract void f();
}
