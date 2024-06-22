/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.will.javatest.thkij4ed.ch11.MapOfList;
import net.will.javatest.thkij4ed.ch14.pets.Individual;
import net.will.javatest.thkij4ed.ch14.pets.Pet;

/**
 * 
 * @author Will
 * @version 2012-10-18
 */
public class IndividualTest {

	public static void main(String[] args) {
		Set<Individual> pets = new TreeSet<Individual>();
		for (List<? extends Pet> lp : MapOfList.petPeople.values())
			for (Pet p : lp)
				pets.add(p);
		System.out.println(pets);
	}

}
