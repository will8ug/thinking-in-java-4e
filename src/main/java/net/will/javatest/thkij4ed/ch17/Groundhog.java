/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

/**
 * 
 * @author Will
 * @version 2012-10-15
 */
public class Groundhog {
	protected int number;
	
	public Groundhog(int n) {
		number = n;
	}
	
	@Override
	public String toString() {
		return "Groundhog #" + number;
	}
}
