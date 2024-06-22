/**
 * 
 */
package net.will.javatest.thkij4ed.ch10.controller;

/**
 * 
 * @author Will
 * @version 2012-10-12
 */
public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	public Event(long delayTime) {
		this.delayTime = delayTime;
		start();
	}
	public void start() {  // allows restarting
		eventTime = System.nanoTime() + delayTime;
	}
	public boolean ready() {
		return System.nanoTime() >= eventTime;
	}
	public abstract void action();
}
