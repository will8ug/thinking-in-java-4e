/**
 * @(#)ShowProperties.java - Will's practices of ThinkingInJava4ed.
 */
package net.will.javatest.thkij4ed.ch02;

/**
 * 
 *
 * @author Will
 * @version v1.0, 2009-3-24
 *
 */
public class ShowProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.getProperties().list(System.out);
		
		System.out.println("============================");
		System.out.println( System.getProperty("user.name") );
		System.out.println( System.getProperty("user.dir") );
		System.out.println( System.getProperty("java.library.path") );
		System.out.println( System.getProperty("file.encoding") );
	}

}
