/**
 * 
 */
package net.will.javatest.thkij4ed.ch07;

/**
 * @author Will
 * @version 2011-10-18
 */
public class NonStaticInstanceInitial {
	{
		System.out.println("in non-static instance initialization.");
	}
	
	public NonStaticInstanceInitial() {
		System.out.println("in constructor.");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NonStaticInstanceInitial();
	}

}
