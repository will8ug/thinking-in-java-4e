/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>Returning a reference to an inner class.</p>
 * 
 * <p>If you want to make an object of the inner class anywhere
 * except from within a <b>non-static</b> method of the outer
 * class, you must specify the type of that object as
 * <em>OuterClassName.InnerClassName</em>, as seen in
 * <code>main()</code>.</p>
 * 
 * @author Will
 * @version 2012-8-29
 */
public class Parcel2 {
	class Contents {
		private int i = 11;
		public int value() {
			return i;
		}
	}

	class Destination {
		private String label;
		Destination(String whereTo) {
			label = whereTo;
		}
		String readLabel() {
			return label;
		}
	}
	
	public Destination to(String s) {
		return new Destination(s);
	}
	
	public Contents contents() {
		return new Contents();
	}

	public void ship(String dest) {
		Contents c = new Contents();
		Destination d = new Destination(dest);
		System.out.println(c.value());
		System.out.println(d.readLabel());
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Parcel2 p = new Parcel2();
		p.ship("Tasmania");
		Parcel2 q = new Parcel2();
		// defining references to inner classes
		Parcel2.Contents c = q.contents();
		Parcel2.Destination d = q.to("Borneo");
		Contents amiokc = q.contents();   // no "Parcel2." is OK
	}

}
