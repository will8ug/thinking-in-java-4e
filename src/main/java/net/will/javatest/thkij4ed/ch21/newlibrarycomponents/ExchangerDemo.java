/**
 * @(#)ExchangerDemo.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.newlibrarycomponents;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.will.javatest.thkij4ed.ch15.BasicGenerator;
import net.will.javatest.thkij4ed.ch15.Generator;

/**
 * <p>
 * An <tt>Exchanger</tt> is a barrier that swaps objects between two
 * tasks. When the tasks enter the barrier, they have one object, and
 * when they leave, they have the object that was formerly held by the
 * other task. <tt>Exchanger</tt>s are typically used when one task is
 * creating objects that are expensive to produce and another task is
 * consuming those objects; this way, more objects can be created at
 * the same time as they are being consumed.
 * </p>
 * <p>
 * The <tt>ExchangerProducer</tt> and <tt>ExchangerConsumer</tt> use
 * a <tt>List&lt;T&gt;</tt> as the object to be exchanged; each one
 * contains an <tt>Exchanger</tt> for this <tt>List&lt;T&gt;</tt>. When
 * you call the <tt>Exchanger.exchange()</tt> method, it blocks until the
 * partner task calls its <tt>exchange()</tt> method, and when both
 * <tt>exchange()</tt> methods have completed, the <tt>List&lt;T&gt;</tt>
 * has been swapped.
 * </p>
 * <p>
 * The <tt>ExchangerProducer</tt> fills a <tt>List</tt>, then swaps the
 * full list for the empty one that the <tt>ExchangerConsumer</tt> hands
 * it. Because of the <tt>Exchanger</tt>, the filling of one list and consuming
 * of the other list can happen simultaneously.
 * </p>
 *
 * @author Will
 * @version 2012-3-21
 */
public class ExchangerDemo {
	static int size = 10;
	static int delay = 2;   // seconds
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();
		List<Fat> producerList = new CopyOnWriteArrayList<Fat>();
		List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();
		
		exec.execute(new ExchangerProducer<Fat>(BasicGenerator
				.create(Fat.class), xc, producerList));
		exec.execute(new ExchangerConsumer<Fat>(xc, consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();
	}
}

class ExchangerProducer<T> implements Runnable {
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	
	public ExchangerProducer(Generator<T> generator,
										Exchanger<List<T>> exchanger,
										List<T> holder) {
		this.generator = generator;
		this.exchanger = exchanger;
		this.holder = holder;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				for (int i = 0; i < ExchangerDemo.size; i++) {
					T value = generator.next();
					holder.add(value);
					System.out.println("Added value: " + value);
				}
				
				// exchange full for empty
				holder = exchanger.exchange(holder);  // ========KEY========
			}
		} catch (InterruptedException e) {
			// OK to terminate this way
		}
	}
	
}

class ExchangerConsumer<T> implements Runnable {
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	
	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
		this.exchanger = exchanger;
		this.holder = holder;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				holder = exchanger.exchange(holder);   // ========KEY========
				for (T x : holder) {
					value = x;   // fetch out value
					System.out.println("Fetched value: " + value);
					holder.remove(x);   // OK for CopyOnWriteArrayList
				}
			}
		} catch (InterruptedException e) {
			// OK to terminate this way
		}
		System.out.println("Final value: " + value);
	}
	
}