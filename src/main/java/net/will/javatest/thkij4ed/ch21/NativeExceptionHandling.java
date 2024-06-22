/**
 * 
 */
package net.will.javatest.thkij4ed.ch21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 *
 * @author Will
 * @version 2011-12-16
 */
public class NativeExceptionHandling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException e) {
			// this statement will NOT execute!
			System.out.println("Exception has been handled!");
		}
	}

}
