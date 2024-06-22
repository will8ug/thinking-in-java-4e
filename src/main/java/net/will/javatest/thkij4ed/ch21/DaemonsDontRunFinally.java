/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.concurrent.TimeUnit;

/**
 * When you run this program, you'll see that the <code>finally</code> clause
 * is not executed, but if you comment out the call to <code>setDaemon()</code>,
 * you'll see that the <code>finally</code> clause <em>is</em> executed.
 *
 * @author Will
 * @version 2011-12-15
 */
public class DaemonsDontRunFinally {
	public static void main(String[] args) {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
	}
}

class ADaemon implements Runnable {
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		} finally {
			System.out.println("This should always run?");
		}
	}
}
