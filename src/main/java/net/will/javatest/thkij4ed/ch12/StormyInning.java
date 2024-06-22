/**
 * 
 */
package net.will.javatest.thkij4ed.ch12;

/**
 * Exception restrictions.
 * 
 * @author Will
 * @version 2011-9-28
 */
public class StormyInning extends Inning implements Storm {
	// interface cannot add exceptions to existing methods from
	// the base class
//	public void event() throws StormException {}    // compile error
//	public void event() throws BaseballException {} // compile error

	// you can choose to not throw any exceptions, even if
	// the base version does
	public void event() {}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}

class BaseballException extends Exception {
	private static final long serialVersionUID = 470526267638233808L;
}
class StormException extends Exception {
	private static final long serialVersionUID = 3074906300226622512L;
}

abstract class Inning {
	public void event() throws BaseballException {
		// NOP
	}
}

interface Storm {
	public void event() throws StormException;
}