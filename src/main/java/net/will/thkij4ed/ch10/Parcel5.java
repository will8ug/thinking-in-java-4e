/**
 * 
 */
package net.will.thkij4ed.ch10;

/**
 * Nesting a class within a method.
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel5 {
	public Destination destination(String s) {
		class PDestination implements Destination {
			private String label;
			private PDestination(String whereTo) {
				label = whereTo;
			}
			@Override
			public String readLabel() {
				return label;
			}
		}
		return new PDestination(s);
	}

	public static void main(String[] args) {
		Parcel5 p = new Parcel5();
		Destination d = p.destination("Will");
		System.out.println(d.readLabel());
	}

}
