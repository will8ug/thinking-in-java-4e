/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import net.will.javatest.thkij4ed.common.CommonVariables;

/**
 * To make things work correctly, you must not only write the important data
 * from the object during the <code>writeExternal()</code> method (there is
 * no default behavior that writes any of the member objects for an
 * <code>Externalizable</code> object), but you must also recover that data
 * in the <code>readExternal()</code> method.
 *
 * @author Will
 * @version 2011-11-20
 */
public class Blip3 implements Externalizable {
	private int i;
	private String s;  // no initialization
	public Blip3() {
		System.out.println("Blip3 Constructor");
		// s, i not initialized
	}
	public Blip3(String x, int a) {
		System.out.println("Blip3(String x, int a)");
		s = x;
		i = a;
		// s, i initialized only in non-default Constructor
	}
	@Override
	public String toString() {
		return s + i;
	}
	
	/* (non-Javadoc)
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip3.writeExternal");
		// you must do this:
		out.writeObject(s);
		out.writeInt(i);
	}
	/* (non-Javadoc)
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.out.println("Blip3.readExternal");
		// you must do this:
		s = (String) in.readObject();
		i = in.readInt();
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Constructing objects: ");
		Blip3 b3 = new Blip3("String", 47);
		System.out.println(b3);
		
		String outfile = CommonVariables.TEMP_OUTPUT_PATH + "Blip3.out";
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outfile));
		System.out.println("Saving object: ");
		out.writeObject(b3);
		out.close();
		
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(outfile));
		System.out.println("Recovering b3:");
		b3 = (Blip3) in.readObject();
		System.out.println(b3);
	}

}
