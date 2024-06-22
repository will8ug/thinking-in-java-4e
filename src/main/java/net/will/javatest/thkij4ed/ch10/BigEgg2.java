/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Proper inheritance of an inner class.
 * 
 * @author Will
 * @version 2012-10-15
 */
public class BigEgg2 extends Egg2 {
	public class Yolk extends Egg2.Yolk {
		public Yolk() { System.out.println("BigEgg2.Yolk()"); }
		public void f() { System.out.println("BigEgg2.Yolk.f()"); }
	}
	
	public BigEgg2() { insertYolk(new Yolk()); }

	public static void main(String[] args) {
		Egg2 e2 = new BigEgg2();
		e2.g();
	}
}

class Egg2 {
	protected class Yolk {
		public Yolk() { System.out.println("Egg2.Yolk()"); }
		public void f() { System.out.println("Egg2.Yolk.f()"); }
	}
	private Yolk y = new Yolk();
	public Egg2() {
		System.out.println("new Egg2()");
	}
	public void insertYolk(Yolk y) {
		this.y = y;
	}
	public void g() {
		y.f();
	}
}