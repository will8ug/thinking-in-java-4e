/**
 * @(#)PriorityBlockingQueueDemo.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.newlibrarycomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author Will
 * @version 2012-3-19
 */
public class PriorityBlockingQueueDemo {
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
		exec.execute(new PrioritizedTaskProducer(queue, exec));
		exec.execute(new PrioritizedTaskConsumer(queue));
	}
}

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
	private Random rand = new Random(47);
	private static int counter = 0;
	private final int id = counter++;
	private final int priority;
	protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();
	public PrioritizedTask(int priority) {
		this.priority = priority;
		sequence.add(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PrioritizedTask o) {
		// ============KEY============
		return this.priority < o.priority ? 1 : (this.priority > o.priority ? -1 : 0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
		} catch (InterruptedException e) {
			// acceptable way to exit
		}
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		return String.format("[%1$-3d]", priority) + " Task " + id;
	}
	
	public String summary() {
		return "(" + id + ":" + priority + ")";
	}
	
	public static class EndSentinel extends PrioritizedTask {
		private ExecutorService exec;
		public EndSentinel(ExecutorService e) {
			super(-1);   // lowest priority in this program
			exec = e;
		}
		@Override
		public void run() {
			int count = 0;
			for (PrioritizedTask pt : sequence) {
				System.out.print(pt.summary());
				if (0 == ++count % 5) {
					System.out.println();
				}
			}
			System.out.println();
			System.out.println(this + " calling shutdownNow()");
			exec.shutdownNow();
		}  // end run()
	}  // end class EndSentinel
}

class PrioritizedTaskProducer implements Runnable {
	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;
	
	public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
		this.queue = queue;
		this.exec = exec;  // used for EndSentinel
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// unbounded queue; never blocks.
		// fill it up fast with random priorities
		for (int i = 0; i < 20; i++) {
			queue.add(new PrioritizedTask(rand.nextInt(10)));
			Thread.yield();
		}
		
		try {
			// trickle in highest-priority jobs:
			for (int i = 0; i < 10; i++) {
				TimeUnit.MILLISECONDS.sleep(250);
				queue.add(new PrioritizedTask(10));
			}
			// add jobs, lowest priority first:
			for (int i = 0; i < 10; i++) {
				queue.add(new PrioritizedTask(i));
			}
			// a sentinel to stop all the tasks:
			queue.add(new PrioritizedTask.EndSentinel(exec));
		} catch (InterruptedException e) {
			// acceptable way to exit
		}
		System.out.println("Finished PrioritizedTaskProducer");
	}
}

class PrioritizedTaskConsumer implements Runnable {
	private PriorityBlockingQueue<Runnable> q;
	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
		this.q = q;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// use current thread to run the task:
				q.take().run();   // ============KEY============
			}
		} catch (InterruptedException e) {
			// acceptable way to exit
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}
	
}