/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Nested classes (static inner classes).
 *
 * @author Will
 * @version 2012-9-5
 */
public class Parcel11 {
	private static class ParcelContents implements Contents {
		private int i = 11;
		@Override
		public int value() { return i; }
	}
	
	protected static class ParcelDestination implements Destination {
		private String label;
		private ParcelDestination(String whereTo) {
			label = whereTo;
		}
		@Override
		public String readLabel() { return label; }
		
		// nested classes can contain other static elements
		public static void f() {}
		static int x = 10;
		static class AnotherLevel {
			public static void f() {}
			static int x = 10;
		}
	}
	
	public static Destination destination(String s) {
		return new ParcelDestination(s);
	}
	public static Contents contents() {
		return new ParcelContents();
	}

	public static void main(String[] args) {
		Contents c = contents();
		Destination d = destination("Tasmania");
		System.out.println(c.value());
		System.out.println(d.readLabel());
	}

}
