/**
 * @(#)TaskItem.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * A Future and the Callable that produced it.
 * 
 * @author Will
 * @version 2012-4-13
 */
public class TaskItem<R, C extends Callable<R>> {
	public final Future<R> future;
	public final C task;

	public TaskItem(Future<R> future, C task) {
		this.future = future;
		this.task = task;
	}
}
