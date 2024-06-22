/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * <p>Dining philosophers without deadlock.</p>
 *
 * @author Will
 * @version 2012-3-7
 */
public class FixedDiningPhilosophers {
	public static void main(String[] args) throws Exception {
		int ponder = 0;
		int size = 5;
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		}
		
		for (int i = 0; i < size; i++) {
			if (i < (size - 1)) {
				exec.execute(new Philosopher(sticks[i], sticks[i+1], i, ponder));
			} else {
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
			}
		}
		
		TimeUnit.SECONDS.sleep(3);
		exec.shutdownNow();
	}
}
