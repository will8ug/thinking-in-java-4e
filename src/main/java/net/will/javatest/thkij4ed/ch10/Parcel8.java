/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Calling the base-class constructor.
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel8 {
	public Wrapping wrapping(int x) {
		// base constructor call:
		return new Wrapping(x) {  // pass constructor argument
			@Override
			public int value() {
				return super.value() * 47;
			}
		};  // semicolon required
	}

	public static void main(String[] args) {
		Parcel8 p = new Parcel8();
		Wrapping w = p.wrapping(10);
		System.out.println(w.value());
	}

}
