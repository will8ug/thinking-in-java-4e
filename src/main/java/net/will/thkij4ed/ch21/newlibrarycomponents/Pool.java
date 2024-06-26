/**
 * @(#)Pool.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch21.newlibrarycomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 
 *
 * @author Will
 * @version 2012-3-21
 */
public class Pool<T> {
	private int size;
	private List<T> items = new ArrayList<T>();
	private volatile boolean[] checkedOut;
	private Semaphore available;
	
	public Pool(Class<T> classObject, int size) {
		this.size = size;
		checkedOut = new boolean[size];
		available = new Semaphore(size, true);
		// load pool with objects that can be checked out:
		for (int i=0; i<size; i++) {
			try {
				// assumes a default constructor:
				items.add(classObject.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public T checkOut() throws InterruptedException {
		available.acquire();  // ========KEY========
		return getItem();
	}
	
	public void checkIn(T x) {
		if (releaseItem(x)) {
			available.release();   // ========KEY========
		}
	}
	
	private synchronized T getItem() {
		for (int i=0; i<size; i++) {
			if (!checkedOut[i]) {
				checkedOut[i] = true;
				return items.get(i);
			}
		}
		return null;  // Semaphore prevents reaching here
	}
	
	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if (-1 == index) {
			return false;   // not in the list
		}
		if (checkedOut[index]) {
			checkedOut[index] = false;
			return true;
		}
		return false;  // wasn't checked out
	}
}
