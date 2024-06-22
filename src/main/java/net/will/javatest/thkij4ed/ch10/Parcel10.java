/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Using "instance initialization" to perform construction
 * on an anonymous inner class.
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel10 {
	public Destination destination(final String dest, final float price) {
		return new Destination() {
			private int cost;
			// instance initialization for each object:
			{
				cost = Math.round(price);
				if (cost > 100) {
					System.out.println("over budget!");
				}
			}
			private String label = dest;
			@Override
			public String readLabel() {	return label; }
		};
	}

	public static void main(String[] args) {
		Parcel10 p = new Parcel10();
		Destination d = p.destination("Tasmania", 101.395F);
		System.out.println(d.readLabel());
	}

}
