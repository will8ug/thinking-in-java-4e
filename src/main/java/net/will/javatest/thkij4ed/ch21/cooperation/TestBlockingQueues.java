/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.cooperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import net.will.javatest.thkij4ed.ch21.LiftOff;

/**
 * Notice that <code>LiftOffRunner</code> can ignore synchronization issues because
 * they are solved by the <code>BlockingQueue</code>.
 *
 * @author Will
 * @version 2012-2-16
 */
public class TestBlockingQueues {
	public static void main(String[] args) {
		test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());   // unlimited size
		test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));   // fixed size
		test("SynchronousQueue", new SynchronousQueue<LiftOff>());   // size of 1
	}
	
	static void test(String msg, BlockingQueue<LiftOff> queue) {
		System.out.print(msg);
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		for (int i=0; i<5; i++) {
			runner.add(new LiftOff(5));
		}
		getkey("Press Enter (" + msg + ")");
		t.interrupt();
		System.out.print("Finished " + msg + " test");
	}
	
	static void getkey() {
		try {
			// compensate for Windows/Linux difference in the length
			// of the result produced by the Enter key:
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	static void getkey(String message) {
		System.out.print(message);
		getkey();
	}
}

class LiftOffRunner implements Runnable {
	private BlockingQueue<LiftOff> rockets;
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	public void add(LiftOff lo) {
		try {
			rockets.put(lo);          // ===========KEY===========
		} catch (InterruptedException e) {
			System.out.println("Interrupted during put()");
		}
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				LiftOff rocket = rockets.take();          // ===========KEY===========
				rocket.run();   // use this thread
			}
		} catch (InterruptedException e) {
			System.out.println("Waking from take()");
		}
		System.out.println("Exiting LiftOffRunner");
	}
}