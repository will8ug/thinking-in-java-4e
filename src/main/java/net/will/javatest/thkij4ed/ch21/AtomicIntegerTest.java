/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Here we've eliminated the <code>synchronized</code> keyword by using 
 * <code>AtomicInteger</code> instead. Because the program doesn't fail,
 * a Timer is added to automatically abort after 5 seconds.
 *
 * @author Will
 * @version 2011-12-20
 */
public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);
	
	public int getValue() {
		return i.get();
	}
	
	private void evenIncrement() {
		i.addAndGet(2);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Aborting");
				System.exit(0);
			}
		}, 5000);  // terminate after 5 seconds
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		exec.execute(ait);
		while (true) {
			int val = ait.getValue();
			if ( val % 2 != 0 ) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
