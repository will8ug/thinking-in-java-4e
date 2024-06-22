/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.concurrent.ThreadFactory;

/**
 * 
 *
 * @author Will
 * @version 2011-12-15
 */
public class DaemonThreadFactory implements ThreadFactory {

	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
