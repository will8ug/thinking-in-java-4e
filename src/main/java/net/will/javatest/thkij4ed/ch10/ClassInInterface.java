/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * @author Will
 * @version 2011-9-17
 */
public interface ClassInInterface {
	void howdy();
	
	class Test implements ClassInInterface {
		public void howdy() {
			System.out.println("howdy!");
		}
		
		public static void main(String[] args) {
			new Test().howdy();
		}
	}
}
