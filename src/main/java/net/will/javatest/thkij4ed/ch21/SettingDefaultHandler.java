/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The default handler is only called if there is no per-thread
 * uncaught exception handler. The system checks for a per-thread
 * version, and if it doesn’t find one it checks to see if the thread
 * group specializes its <code>uncaughtException()</code> method; if
 * not, it calls the <code>defaultUncaughtExceptionHandler</code>.
 *
 * @author Will
 * @version 2011-12-16
 */
public class SettingDefaultHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}

}
