/**
 * 
 */
package net.will.javatest.thkij4ed.ch18.xfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 * <p>
 * Even opening the file and reading in the object <code>mystery</code>
 * requires the <code>Class</code> object for <code>Alien</code>; the
 * JVM cannot find <code>Alien.class</code> (unless it happens to be in
 * the classpath, ...). You'll get a <code>ClassNotFoundException</code>.
 * (Once again, all evidence of alien life vanishes before proof of its
 * existence can be verified!) The JVM must be able to find the associated
 * .class file.
 * </p><p>
 * Exception in thread "main" java.lang.ClassNotFoundException:
 * net.will.javatest.thkij4ed.ch18.Alien
 * </p>
 *
 * @author Will
 * @version 2011-11-20
 */
public class ThawAlien {
	static String outfile = "G:\\x.file";
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
//		ObjectInput in = new ObjectInputStream(new FileInputStream(new File("..", "x.file")));
		ObjectInput in = new ObjectInputStream(new FileInputStream(new File(outfile)));
		Object mystery = in.readObject();   // throws an Exception here
		System.out.println(mystery.getClass());
	}

}
