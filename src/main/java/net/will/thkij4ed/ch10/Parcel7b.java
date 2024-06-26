/**
 * 
 */
package net.will.thkij4ed.ch10;

/**
 * Expanded version of Parcel7.java
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel7b {
	class MyContents implements Contents {
		private int i = 11;
		@Override
		public int value() {
			return i;
		}
	}

	public Contents contents() {
		return new MyContents();
	}

	public static void main(String[] args) {
		Parcel7b p = new Parcel7b();
		Contents c = p.contents();
		System.out.println(c.value());
	}
}
