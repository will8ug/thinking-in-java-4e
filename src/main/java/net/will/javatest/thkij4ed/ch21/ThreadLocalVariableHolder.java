/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * <em>Thread local storage</em> is a mechanism that automatically creates
 * different storage for the same variable, for each different thread
 * that uses an object.
 * </p>
 * <p>
 * <code>ThreadLocal</code> objects are usually stored as <code>static</code>
 * fields. Notice that <code>increment()</code> and <code>get()</code> are
 * not <code>synchronized</code>, because <code>ThreadLocal</code> guarantees
 * that no race condition can occur.
 * </p>
 * <p>
 * When you run this program, you'll see evidence that the individual threads
 * are each allocated their own storage, since each one keeps its own
 * count even though there's only one <code>ThreadLocalVariableHolder</code>
 * object.</p>
 *
 * @author Will
 * @version 2011-12-24
 */
public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random rand = new Random(47);
		@Override
		protected synchronized Integer initialValue() {
			return rand.nextInt(10000);
		}
	};

	public static void increment() {
		value.set(value.get() + 1);
	}
	
	public static int get() {
		return value.get();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i=0; i<5; i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(1);  // run for a while
		exec.shutdownNow();    // all accessors will quit
	}

}

class Accessor implements Runnable {
	private final int id;
	public Accessor(int idn) {
		id = idn;
	}
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}
}