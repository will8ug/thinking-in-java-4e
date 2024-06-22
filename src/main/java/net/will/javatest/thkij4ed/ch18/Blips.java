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
 * With an <code>Externalizable</code> object, all the normal default construction
 * behavior occurs (including the initializations at the point of field definition),
 * and <em>then</em> <code>readExternal()</code> is called. This is different from
 * recovering a <code>Serializable</code> object.
 *
 * @author Will
 * @version 2011-11-20
 */
public class Blips {
	private static String outfile = CommonVariables.TEMP_OUTPUT_PATH + "Blips.out";

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Constructing objects:");
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();
		
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(outfile));
		System.out.println("Saving objects:");
		o.writeObject(b1);
		o.writeObject(b2);
		o.close();
		
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(outfile));
		System.out.println("Recovering b1:");
		b1 = (Blip1) i.readObject();
		// throws an exception:
		// Exception in thread "main" java.io.InvalidClassException:
		//     net.will.javatest.thkij4ed.ch18.Blip2;
		//     net.will.javatest.thkij4ed.ch18.Blip2;
		//     no valid constructor
		// ...
		// Caused by: java.io.InvalidClassException: net.will.javatest.thkij4ed.ch18.Blip2;
		//     no valid constructor
//		System.out.println("Recovering b2:");
//		b2 = (Blip2) i.readObject();
	}

}

class Blip1 implements Externalizable {
	public Blip1() {
		System.out.println("Blip1 Constructor");
	}
	/* (non-Javadoc)
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1.writeExternal");
	}
	/* (non-Javadoc)
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.out.println("Blip1.readExternal");
	}
}

class Blip2 implements Externalizable {
	Blip2() {
		// not public Constructor will cause an Exception upon recovery.
		System.out.println("Blip2 Constructor");
	}
	/* (non-Javadoc)
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2.writeExternal");
	}
	/* (non-Javadoc)
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.out.println("Blip2.readExternal");
	}
}
