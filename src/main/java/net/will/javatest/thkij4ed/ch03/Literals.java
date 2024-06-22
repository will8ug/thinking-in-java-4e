/**
 * 
 */
package net.will.javatest.thkij4ed.ch03;

/**
 * @author Will
 * @version 2011-09-03
 */
public class Literals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char c = 0xffff;
		System.out.println(Integer.toBinaryString(c));
		System.out.println(Long.toBinaryString(c));
		
		float f = 1024.8F;
		System.out.println(Float.toHexString(f));
		System.out.println(Double.toHexString(f));
	}

}
