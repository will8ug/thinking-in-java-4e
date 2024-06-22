/**
 * 
 */
package net.will.javatest.thkij4ed.ch11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.will.javatest.thkij4ed.ch14.pets.Cat;
import net.will.javatest.thkij4ed.ch14.pets.Cymric;
import net.will.javatest.thkij4ed.ch14.pets.Dog;
import net.will.javatest.thkij4ed.ch14.pets.Mutt;
import net.will.javatest.thkij4ed.ch14.pets.Person;
import net.will.javatest.thkij4ed.ch14.pets.Pet;
import net.will.javatest.thkij4ed.ch14.pets.Pug;
import net.will.javatest.thkij4ed.ch14.pets.Rat;

/**
 * 
 * @author Will
 * @version 2012-10-18
 */
public class MapOfList {
	public static Map<Person, List<? extends Pet>> petPeople = new HashMap<Person, List<? extends Pet>>();
	static {
		petPeople.put(new Person("Dawn"),
				Arrays.asList(new Cymric("Molly"), new Mutt("Spot")));
		petPeople.put(new Person("Kate"), Arrays.asList(new Cat("Shackleton"),
				new Cat("Elsie May"), new Dog("Margrett")));
		petPeople.put(new Person("Marilyn"), Arrays.asList(new Pug(
				"Louie aka Louis Snorkelstein Dupree"), new Cat(
				"Stanford aka Stinky el Negro"), new Cat("Pinkola")));
		petPeople.put(new Person("Luke"),
				Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy")));
		petPeople.put(new Person("Isaac"), Arrays.asList(new Rat("Freckly")));
	}

	public static void main(String[] args) {
		System.out.println("People: " + petPeople.keySet());
		System.out.println("Pets: " + petPeople.values());
		for (Person person : petPeople.keySet()) {
			System.out.println(person + " has:");
			for (Pet pet : petPeople.get(person))
				System.out.println("    " + pet);
		}
	}
}
