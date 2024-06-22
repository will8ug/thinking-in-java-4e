/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>
 * An anonymous inner class that performs initialization.
 * A briefer version of Parcel5.java.
 * </p>
 * <p>
 * If you're defining an anonymous inner class and want to use
 * an object that's defined outside the anonymous inner class,
 * the compiler requires that the argument reference be
 * <b>final</b>, as you see in the argument to
 * <code>destination()</code>. If you forget, you'll get a
 * compile-time error message.
 * </p>
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel9 {
	// argument must be final to use inside anonymous inner class
	public Destination destination(final String dest) {
		return new Destination() {
			private String label = dest;
			@Override
			public String readLabel() {
				return label;
			}
		};
	}

	public static void main(String[] args) {
		Parcel9 p = new Parcel9();
		Destination d = p.destination("ok");
		System.out.println(d.readLabel());
	}

}
