/**
 * @(#)DaemonThreadPoolExecutor.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * An util Class.
 * 
 * @author Will
 * @version 2012-4-20
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
	}
}
