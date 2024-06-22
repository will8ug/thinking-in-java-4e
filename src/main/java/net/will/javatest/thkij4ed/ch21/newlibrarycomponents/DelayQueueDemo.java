/**
 * @(#)DelayQueueDemo.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.newlibrarycomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Here’s an example where the <tt>Delayed</tt> objects are themselves
 * tasks, and the <tt>DelayedTaskConsumer</tt> takes the most "urgent"
 * task (the one that has been expired for the longest time) off the queue
 * and runs it. Note that <tt>DelayQueue</tt> is thus a variation of a
 * priority queue.
 * </p>
 * <p>
 * Note that because <tt>DelayedTaskConsumer</tt> is itself a task, it
 * has its own <tt>Thread</tt> which it can use to run each task that
 * comes out of the queue. Since the tasks are being performed in queue
 * priority order, there’s no need in this example to start separate threads
 * to run the <tt>DelayedTasks</tt>.
 * </p>
 *
 * @author Will
 * @version 2012-3-16
 */
public class DelayQueueDemo {
	public static void main(String[] args) {
		Random rand = new Random(47);
		ExecutorService exec = Executors.newCachedThreadPool();
		DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
		// fill with tasks that have random delays:
		for (int i = 0; i < 20; i++) {
			queue.put(new DelayedTask(rand.nextInt(5000)));
		}
		// set the stopping point:
		queue.add(new DelayedTask.EndSentinel(5000, exec));
		exec.execute(new DelayedTaskConsumer(queue));
	}
}

class DelayedTask implements Runnable, Delayed {
	private static int counter = 0;
	private final int id = counter++;
	private final int delta;
	private final long alpha;   // test
	private final long trigger;
	protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
	
	public DelayedTask(int delayInMilliseconds) {
		delta = delayInMilliseconds;
		alpha = TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		trigger = System.nanoTime() + alpha;
		sequence.add(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed o) {
		DelayedTask that = (DelayedTask) o;
		if (this.trigger < that.trigger) {
			return -1;
		}
		if (this.trigger > that.trigger) {
			return 1;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		// the number returned here must become more and more small
		// ============KEY============
		return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
		// error occurs here: no time elapse
//		return unit.convert(delta, TimeUnit.MILLISECONDS);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.print(this + " ");
	}
	
	@Override
	public String toString() {
		return String.format("[%1$-4d]", delta) + " Task " + id;
	}
	
	public String summary() {
		return "(" + id + ": " + delta + " | " + alpha + ")";
	}
	
	public static class EndSentinel extends DelayedTask {
		private ExecutorService exec;
		public EndSentinel(int delay, ExecutorService e) {
			super(delay);
			exec = e;
		}
		@Override
		public void run() {
			System.out.println();
			for (DelayedTask dt : sequence) {
				System.out.println(dt.summary());
			}
			System.out.println(this + " calling shutdownNow()");
			exec.shutdownNow();
		}
	}
}

class DelayedTaskConsumer implements Runnable {
	private DelayQueue<DelayedTask> q;
	public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
		this.q = q;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("ready for take()");
				// ============KEY============
				q.take().run(); // run task with the current thread
			}
		} catch (InterruptedException e) {
			// acceptable way to exit
		}
		System.out.println("Finished DelayedTaskConsumer");
	}
}