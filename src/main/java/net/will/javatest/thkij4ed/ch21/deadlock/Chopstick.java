/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.deadlock;

/**
 * Chopsticks for dining philosophers.
 *
 * @author Will
 * @version 2012-3-7
 */
public class Chopstick {
	private boolean taken = false;
	
	public synchronized void take() throws InterruptedException {
		while (taken) {
			wait();
		}
		taken = true;
	}
	
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
