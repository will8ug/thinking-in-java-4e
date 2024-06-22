/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.will.javatest.thkij4ed.ch14.pets.Cat;
import net.will.javatest.thkij4ed.ch14.pets.Dog;
import net.will.javatest.thkij4ed.ch14.pets.Pet;

/**
 * A checked container will throw a ClassCastException at the point you try to
 * insert an improper object, as opposed to a pre-generic (raw) container which
 * would inform you that there was a problem when you pulled the object out.
 * 
 * @author Will
 * @version 2011-10-22
 */
public class CheckedList {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void oldStyleMethod(List probablyDogs) {
		probablyDogs.add(new Cat());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Dog> dogs1 = new ArrayList<Dog>();
		oldStyleMethod(dogs1);   // quietly accepts a Cat
		List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
		try {
			oldStyleMethod(dogs2);    // throws an Exception
		} catch (Exception e) {
			System.out.println(e);
		}
		// derived types work fine:
		List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
		pets.add(new Dog());
		pets.add(new Cat());
	}

}
