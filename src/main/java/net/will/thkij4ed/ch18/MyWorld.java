/**
 * 
 */
package net.will.thkij4ed.ch18;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * As long as you're serializing everything to a single stream,
 * you'll recover the same web of objects that you wrote, with
 * no accidental duplication of objects. Of course, you can
 * change the state of your objects in between the time you
 * write the first and the last, but that's your responsibility;
 * the objects will be written in whatever state they are in
 * (and with whatever connections they have to other objects) at
 * the time you serialize them.
 *
 * @author Will
 * @version 2012-8-28
 */
public class MyWorld {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		House house = new House();
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("Bosco the dog", house));
		animals.add(new Animal("Ralph the hamster", house));
		animals.add(new Animal("Molly the cat", house));
		System.out.println("animals: " + animals);
		
		ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
		ObjectOutputStream o1 = new ObjectOutputStream(buf1);
		o1.writeObject(animals);
		o1.writeObject(animals);  // write a 2nd set
		// write to a different stream
		ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
		ObjectOutputStream o2 = new ObjectOutputStream(buf2);
		o2.writeObject(animals);
		
		// now get them back
		ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(
				buf1.toByteArray()));
		ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(
				buf2.toByteArray()));
		List
			animals1 = (List) in1.readObject(),
			animals2 = (List) in1.readObject(),
			animals3 = (List) in2.readObject();
		System.out.println("animals1: " + animals1);
		System.out.println("animals2: " + animals2);
		System.out.println("animals3: " + animals3);
	}
}

class House implements Serializable {
	private static final long serialVersionUID = -3366149912932489892L;
}

class Animal implements Serializable {
	private static final long serialVersionUID = 5953639076320640357L;
	private String name;
	private House preferredHouse;
	Animal(String nm, House h) {
		name = nm;
		preferredHouse = h;
	}
	@Override
	public String toString() {
		return name + "[" + super.toString() + "], " + preferredHouse + "\n";
	}
}