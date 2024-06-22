/**
 * 
 */
package net.will.javatest.thkij4ed.ch06;

/**
 * @author Will
 * @version 2011-10-26
 */
public class SetType {
	private int i;
	int j;
	protected int k;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SetType s = new SetType();
		System.out.println(s.test());
	}
	
	public int test() {
		SetType s = new SetType();
		s.i = 5;   // you can visit i here, though s is a new object rather than this object itself.
		return s.i;
	}
}

class AnotherClass {
	public void test() {
		SetType s = new SetType();
//		s.i = 5;   // compile error: the field SetType.i is not visible
		s.j = 1;
		s.k = 2;
	}
}
