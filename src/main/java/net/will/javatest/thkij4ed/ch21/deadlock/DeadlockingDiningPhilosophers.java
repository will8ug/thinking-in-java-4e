/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * <p>Demonstrates how deadlock can be hidden in a program.</p>
 * 
 * <p>
 * You will observe that if the <tt>Philosopher</tt>s spend very little time
 * thinking, they will all be competing for the <tt>Chopstick</tt>s while
 * they try to eat, and deadlock will happen much more quickly.
 * </p>
 *
 * @author Will
 * @version 2012-3-7
 */
public class DeadlockingDiningPhilosophers {
	public static void main(String[] args) throws Exception {
		int ponder = 5;  // KEY factor
		int size = 5;
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		}
		
		for (int i = 0; i < size; i++) {
			exec.execute(new Philosopher(sticks[i], sticks[(i+1)%size], i, ponder));
		}
		
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}
