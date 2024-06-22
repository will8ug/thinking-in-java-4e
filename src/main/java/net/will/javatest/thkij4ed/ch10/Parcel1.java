/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>Creating inner classes. Also a test for static fields in ordinary
 * inner class.</p>
 * 
 * <p>Fields and methods in ordinary inner classes can only be at the
 * outer level of a class, so ordinary inner classes cannot have static
 * data, static fields, or nested classes. However, nested classes can
 * have all of these</p>
 *
 * @author Will
 * @version 2012-8-29
 */
public class Parcel1 {
	class Contents {
		private int i = 11;

		public int value() {
			return i;
		}
	}

	class Destination {
		private String label;
		// compile error:
		// The field i cannot be declared static; static fields can 
		// only be declared in static or top level types
//		static int i=5;

		Destination(String whereTo) {
			label = whereTo;
		}

		String readLabel() {
			return label;
		}
	}

	// Using inner classes looks just like
	// using any other class, within Parcel1:
	@SuppressWarnings("unused")
	public void ship(String dest) {
		Contents c = new Contents();
		Destination d = new Destination(dest);
		System.out.println(d.readLabel());
	}

	public static void main(String[] args) {
		Parcel1 p = new Parcel1();
		p.ship("Tasmania");
	}

}
