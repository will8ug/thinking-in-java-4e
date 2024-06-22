/**
 * @(#)Fat.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.newlibrarycomponents;

/**
 * 
 *
 * @author Will
 * @version 2012-3-21
 */
public class Fat {
	@SuppressWarnings("unused")
	private volatile double d;   // prevent optimization
	private static int counter = 0;
	private final int id = counter++;
	
	public Fat() {
		// expensive, interruptible operation:
		for (int i = 1; i < 10000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}
	
	public void operation() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return "Fat id: " + id;
	}
}
