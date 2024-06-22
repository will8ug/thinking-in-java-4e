/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.cooperation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Only the tasks that are waiting on a particular lock are awoken when
 * <code>notifyAll()</code> is called for that lock.
 * </p><p>
 * From the output, you can see that even though a <code>Task2</code> object
 * exists and is blocked on <code>Task2.blocker</code>, none of the
 * <code>notify()</code> or <code>notifyAll()</code> calls on <code>Task.blocker</code>
 * causes the <code>Task2</code> object to wake up.
 * </p>
 *
 * @author Will
 * @version 2012-2-13
 */
public class NotifyVSNotifyAll {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i=0; i<5; i++) {
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;
			@Override
			public void run() {
				if (prod) {
					System.out.print("\nnotify() ");
					Task.blocker.prod();
					prod = false;
				} else {
					System.out.print("\nnotifyAll() ");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);   // run every .4 second
		TimeUnit.SECONDS.sleep(5);  // run for a while...
		timer.cancel();
		System.out.println("\nTimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("Task2.blocker.prodAll() ");
		Task2.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\nShutting down");
		exec.shutdownNow();   // interrupt all tasks
	}

}

class Blocker {
	synchronized void waitingCall() {
		try {
			while (!Thread.interrupted()) {
				wait();
				System.out.print(Thread.currentThread() + " ");
			}
		} catch (InterruptedException e) {
			// OK to exit this way
		}
	}
	
	synchronized void prod() {
		notify();
	}
	
	synchronized void prodAll() {
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}

class Task2 implements Runnable {
	// a separate Blocker object:
	static Blocker blocker = new Blocker();
	public void run() {
		blocker.waitingCall();
	}
}
