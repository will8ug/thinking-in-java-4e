/**
 * 
 */
package net.will.javatest.thkij4ed.ch21.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * A dining philosopher.
 *
 * @author Will
 * @version 2012-3-7
 */
public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	
	public Philosopher(Chopstick left, Chopstick right, int ident, int ponder) {
		this.left = left;
		this.right = right;
		id = ident;
		ponderFactor = ponder;
	}
	
	private void pause() throws InterruptedException {
		if (0 == ponderFactor) {
			return;
		}
		
		TimeUnit.MILLISECONDS.sleep( rand.nextInt(ponderFactor * 250) );
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println(this + " thinking");
				pause();
				// philosopher becomes hungry
				System.out.println(this + " grabbing right");
				right.take();
				System.out.println(this + " grabbing left");
				left.take();
				System.out.println(this + " eating");
				pause();
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			System.out.println(this + " exiting via interrupt");
		}
	}
	
	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
