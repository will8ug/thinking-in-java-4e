/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates the "transient" keyword.
 *
 * @author Will
 * @version 2012-8-27
 */
public class Logon implements Serializable {
	private static final long serialVersionUID = -8173854674668001785L;
	private Date date = new Date();
	private String username;
	private transient String password;

	public static void main(String[] args) throws FileNotFoundException,
			IOException, InterruptedException, ClassNotFoundException {
		Logon a = new Logon("Hulk", "myLittlePony");
		System.out.println("logon a = " + a);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
				"output/Logon.out"));
		o.writeObject(a);
		o.close();
		
		TimeUnit.SECONDS.sleep(1);  // delay
		
		// now get them back
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"output/Logon.out"));
		System.out.println("recovering object at " + new Date());
		a = (Logon) in.readObject();
		System.out.println("logon a = " + a);
	}
	
	public Logon(String name, String pwd) {
		username = name;
		password = pwd;
	}
	
	@Override
	public String toString() {
		return "logon info: \n username: " + username + "\n date: " + date
				+ "\n password: " + password;
	}

}
