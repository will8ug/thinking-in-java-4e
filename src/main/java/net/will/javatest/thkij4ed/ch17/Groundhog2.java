/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

/**
 * A class that's used as a key in a HashMap must override
 * <code>hashCode()</code> and <code>equals()</code>.
 * 
 * @author Will
 * @version 2012-10-15
 */
public class Groundhog2 extends Groundhog {
	public Groundhog2(int n) { super(n); }
	
	@Override
	public int hashCode() {
		return number;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Groundhog2 && (number == ((Groundhog2) o).number);
	}
}
