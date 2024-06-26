/**
 * 
 */
package net.will.thkij4ed.ch21.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Producers and consumers.
 *
 * @author Will
 * @version 2012-2-14
 */
public class Restaurant {
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Restaurant();
	}
}

class Meal {
	private final int orderNum;
	public Meal(int orderNum) { this.orderNum = orderNum; }
	public String toString() { return "Meal " + orderNum; }
}

class WaitPerson implements Runnable {
	private Restaurant restaurant;
	public WaitPerson(Restaurant r) { restaurant = r;}
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (null == restaurant.meal) {
						wait();  // for the chef to produce a meal
					}
				}
				
				System.out.println("Waitperson got " + restaurant.meal);
				
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();   // ready for another
				}
			}
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	private int count = 0;
	public Chef(Restaurant r) { this.restaurant = r; }
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();    // for the meal to be taken
					}
				}
				
				if (++count >= 10) {
					System.out.println("Out of food, closing");
					restaurant.exec.shutdownNow();
				}
				System.out.print("Order up! ");
				
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			System.out.println("Chef interrupted");
		}
	}
	
}