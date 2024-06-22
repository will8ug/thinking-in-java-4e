/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Controlling serialization by adding your own writeObject()
 * and readObject() methods.
 *
 * @author Will
 * @version 2012-8-28
 */
public class SerialCtl implements Serializable {
	private static final long serialVersionUID = 6401957630293730192L;
	private String a;
	private transient String b;
	
	public SerialCtl(String aa, String bb) {
		a = "Non transient: " + aa;
		b = "Transient: " + bb;
	}
	
	public String toString() {
		return a + "\n" + b;
	}
	
	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
		stream.writeObject(b);
	}

	private void readObject(ObjectInputStream stream) throws IOException,
			ClassNotFoundException {
		stream.defaultReadObject();
		b = (String) stream.readObject();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerialCtl sc = new SerialCtl("Test1", "Test2");
		System.out.println("Before:\n" + sc);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(buf);
		out.writeObject(sc);
		// now get it back:
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				buf.toByteArray()));
		SerialCtl sc2 = (SerialCtl) in.readObject();
		System.out.println("After:\n" + sc2);
	}

}
