/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 
 *
 * @author Will
 * @version 2011-11-20
 */
public class FreezeAlien {
	static String outfile = "G:\\x.file";

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(outfile));
		Alien quellek = new Alien();
		out.writeObject(quellek);
	}

}
