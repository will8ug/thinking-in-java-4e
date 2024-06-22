/**
 * @(#)SemaphoreDemo.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.newlibrarycomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This example relies on the client of the <tt>Pool</tt> to be rigorous
 * and to voluntarily check items back in, which is the simplest solution
 * when it works.
 *
 * @author Will
 * @version 2012-3-21
 */
public class SemaphoreDemo {
	final static int SIZE = 15;
	
	public static void main(String[] args) throws Exception {
		final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new CheckoutTask<Fat>(pool));
		}
		System.out.println("All CheckoutTasks created");
		
		List<Fat> list = new ArrayList<Fat>();
		for (int i = 0; i < SIZE; i++) {
			Fat f = pool.checkOut();
			System.out.println(i + ": main() thread checked out");
			f.operation();
			list.add(f);
		}
		
		Future<?> blocked = exec.submit(new Runnable() {
			@Override
			public void run() {
				try {
					// Semaphore prevents additional checkout, so call is blocked:
					pool.checkOut();
				} catch (InterruptedException e) {
					System.out.println("checkOut() Interrupted");
				}
			}
		});
		TimeUnit.SECONDS.sleep(2);
		blocked.cancel(true);  // break out of blocked call
		
		System.out.println("Checking in objects in " + list);
		for (Fat f : list) {
			pool.checkIn(f);
		}
		for (Fat f : list) {
			pool.checkIn(f); // second checkIn ignored
		}
		exec.shutdown();
	}
}

// a task to check a resource out of a pool
class CheckoutTask<T> implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private Pool<T> pool;
	public CheckoutTask(Pool<T> pool) {
		this.pool = pool;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			T item = pool.checkOut();
			System.out.println(this + " checked out " + item);
			TimeUnit.SECONDS.sleep(1);
			System.out.println(this + " checking in " + item);
			pool.checkIn(item);
		} catch (InterruptedException e) {
			// acceptable way to terminate
		}
	}

	@Override
	public String toString() {
		return "CheckoutTask " + id;
	}
}