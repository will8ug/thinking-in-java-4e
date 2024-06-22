/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

/**
 * 
 *
 * @author Will
 * @version 2011-12-17
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;
	
	public abstract int next();
	
	public void cancel() {
		canceled = true;
	}
	
	public boolean isCanceled() {
		return canceled;
	}
}
