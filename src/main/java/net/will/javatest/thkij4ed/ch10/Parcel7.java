/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Returning an instance of an anonymous inner class.
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel7 {
	public Contents contents() {
		return new Contents() {  // Insert a class definition
			private int i = 11;
			@Override
			public int value() {
				return i;
			}
		};  // semicolon required in this case
	}

	public static void main(String[] args) {
		Parcel7 p = new Parcel7();
		Contents c = p.contents();
		System.out.println(c.value());
	}

}
