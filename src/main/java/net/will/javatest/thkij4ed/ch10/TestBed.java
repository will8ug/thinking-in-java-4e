/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Putting test code in a nested class.
 *
 * @author Will
 * @version 2012-9-5
 */
public class TestBed {
	public void f() { System.out.println("f()"); }

	public static class Tester {
		public static void main(String[] args) {
			TestBed t = new TestBed();
			t.f();
		}
	}
}
