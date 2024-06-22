/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * An inner class cannot be overriden like a method.
 * 
 * @author Will
 * @version 2012-10-15
 */
public class BigEgg extends Egg {
	public class Yolk {
		public Yolk() {
			System.out.println("BigEgg.Yolk()");
		}
	}

	public static void main(String[] args) {
		new BigEgg();
	}

}

class Egg {
	private Yolk y;
	protected class Yolk {
		public Yolk() { System.out.println("Egg.Yolk()"); }
	}
	public Egg() {
		System.out.println("new Egg()");
		y = new Yolk();
		System.out.println(y);
	}
}