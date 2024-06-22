/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.cooperation;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>A toaster that uses queues.</p>
 * <p>
 * Note that there is no explicit synchronization (using <code>Lock</code>
 * objects or the <code>synchronized</code> keyword) because the
 * synchronization is implicitly managed by the queues (which synchronize
 * internally) and by the design of the system - each piece of
 * <code>Toast</code> is only operated on by one task at a time.
 * Because the queues block, processes suspend and resume automatically.
 * You can see that the simplification produced by <code>BlockingQueue</code>s
 * can be quite dramatic. The coupling between the classes that would exist
 * with explicit <code>wait()</code> and <code>notifyAll()</code>
 * statements is eliminated because each class communicates only with
 * its <code>BlockingQueue</code>s.
 * </p>
 *
 * @author Will
 * @version 2012-2-17
 */
public class ToastOMatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue();
		ToastQueue butteredQueue = new ToastQueue();
		ToastQueue finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Toast {
	public enum Status { DRY, BUTTERED, JAMMED }
	private Status status = Status.DRY;
	private final int id;
	public Toast(int idn) { id = idn; }
	public void butter() { status = Status.BUTTERED; }
	public void jam() { status = Status.JAMMED; }
	public Status getStatus() { return status; }
	public int getId() { return id; }
	@Override
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
	private static final long serialVersionUID = 670760614095873023L;
}

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random rand = new Random(47);
	public Toaster(ToastQueue tq) { toastQueue = tq; }
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				// make toast
				Toast t = new Toast(count++);
				System.out.println(t);
				// insert into queue
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Toaster interrupted.");
		}
		System.out.println("Toaster off.");
	}
}

// apply butter to toast
class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;
	public Butterer(ToastQueue dry, ToastQueue buttered) {
		dryQueue = dry;
		butteredQueue = buttered;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// blocks until next piece of toast is available
				Toast t = dryQueue.take();
				t.butter();
				System.out.println(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Butterer interrupted.");
		}
		System.out.println("Butterer off.");
	}
}

// apply jam to buttered toast
class Jammer implements Runnable {
	private ToastQueue butteredQueue, finishedQueue;
	public Jammer(ToastQueue buttered, ToastQueue finished) {
		butteredQueue = buttered;
		finishedQueue = finished;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// blocks until next piece of toast is available
				Toast t = butteredQueue.take();
				t.jam();
				System.out.println(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			System.out.println("Jammer interrupted.");
		}
		System.out.println("Jammer off.");
	}
}

// consume the toast
class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int counter = 0;
	public Eater(ToastQueue finished) {
		finishedQueue = finished;
	}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// blocks until next piece of toast is available
				Toast t = finishedQueue.take();
				// verify that the toast is coming in order,
				// and that all pieces are getting jammed
				if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
					System.out.println(">>>> Error: " + t);
					System.exit(1);
				} else {
					System.out.println("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Eater interrupted.");
		}
		System.out.println("Eater off.");
	}
}
