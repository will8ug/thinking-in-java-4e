/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * My test result: no <code>InterruptedException</code> will be thrown.
 *
 * @author Will
 * @version 2011-12-15
 */
public class DaemonFromFactory implements Runnable {

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + ": " + this);
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors
				.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute( new DaemonFromFactory() );
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500);  // run for a while
	}

}
