/**
 * @(#)FastSimulation.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.performancetuning;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Although <tt>Atomic</tt> objects perform atomic operations like
 * <tt>decrementAndGet()</tt>, some <tt>Atomic</tt> classes also allow you to
 * perform what is called "optimistic locking." This means that you do not
 * actually use a mutex when you are performing a calculation, but after the
 * calculation is finished and you’re ready to update the <tt>Atomic</tt>
 * object, you use a method called <tt>compareAndSet()</tt>. You hand it the old
 * value and the new value, and if the old value doesn’t agree with the value it
 * finds in the <tt>Atomic</tt> object, the operation fails—this means that some
 * other task has modified the object in the meantime. Remember that we would
 * ordinarily use a mutex (<tt>synchronized</tt> or <tt>Lock</tt>) to prevent
 * more than one task modifying an object at the same time, but here we are
 * "optimistic" by leaving the data unlocked and hoping that no other task comes
 * along and modifies it. Again, all this is done in the name of performance—by
 * using an <tt>Atomic</tt> instead of <tt>synchronized</tt> or <tt>Lock</tt>,
 * you might gain performance benefits.
 * </p>
 * <p>
 * Note that no mutexes appear in the program.
 * </p>
 * 
 * @author Will
 * @version 2012-4-2
 */
public class FastSimulation {
	static final int N_ELEMENTS = 100000;
	static final int N_GENES = 30;
	static final int N_EVOLVERS = 50;
	static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
	static Random rand = new Random(47);

	static class Evolver implements Runnable {
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				// Randomly select an element to work on:
				int element = rand.nextInt(N_ELEMENTS);
				for (int i = 0; i < N_GENES; i++) {
					int previous = element - 1;
					if (previous < 0)
						previous = N_ELEMENTS - 1;
					int next = element + 1;
					if (next >= N_ELEMENTS)
						next = 0;

					// =====KEY START=====
					int oldvalue = GRID[element][i].get();
					// Perform some kind of modeling calculation:
					int newvalue = oldvalue + GRID[previous][i].get()
							+ GRID[next][i].get();
					newvalue /= 3; // Average the three values
					
					if (!GRID[element][i].compareAndSet(oldvalue, newvalue)) {
						// Policy here to deal with failure. Here, we
						// just report it and ignore it; our model
						// will eventually deal with it.
						System.out.println("Old value changed from " + oldvalue);
					}
					// =====KEY END=====
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < N_ELEMENTS; i++)
			for (int j = 0; j < N_GENES; j++)
				GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
		for (int i = 0; i < N_EVOLVERS; i++)
			exec.execute(new Evolver());
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}
