/**
 * @(#)CountDownLatchDemo.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.newlibrarycomponents;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The tasks that call <tt>countDown()</tt> are not blocked when they
 * make that call. Only the call to <tt>await()</tt> is blocked until the
 * count reaches zero.
 * 
 * @author Will
 * @version 2012-3-8
 */
public class CountDownLatchDemo {
	static final int SIZE = 50;
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		// all must share a single CountDownLatch object:
		CountDownLatch ltc = new CountDownLatch(SIZE);
		for (int i=0; i<10; i++) {
			exec.execute(new WaitingTask(ltc));
		}
		for (int i=0; i<SIZE; i++) {
			exec.execute(new TaskPortion(ltc));
		}
		System.out.println("Launched all tasks");
		exec.shutdown();   // quit when all tasks complete
	}
}

// performs some portion of a task
class TaskPortion implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;
	TaskPortion(CountDownLatch ltc) {
		latch = ltc;
	}
	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();   // ============KEY============
		} catch (InterruptedException e) {
			// acceptable way to exit
		}
	}
	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + " completed.");
	}
	@Override
	public String toString() {
		return String.format("%1$-3d", id);
	}
}

// waits on the CountDownLatch
class WaitingTask implements Runnable {
	private static int counter = 0;
	private final int id = counter++;
	private final CountDownLatch latch;
	WaitingTask(CountDownLatch ltc) {
		latch = ltc;
	}
	@Override
	public void run() {
		try {
			latch.await();    // ============KEY============
			System.out.println("Latch barrier passed for " + this);
		} catch (InterruptedException e) {
			System.out.println(this + " interrupted.");
		}
	}
	@Override
	public String toString() {
		return String.format("WaitingTask %1$-3d", id);
	}
}