/**
 * 
 */
package net.will.thkij4ed.ch14.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Will
 * @version 2012-10-18
 */
public abstract class PetCreator {
	private Random rand = new Random(47);

	// The List of the different types of Pet to create:
	public abstract List<Class<? extends Pet>> types();

	public Pet randomPet() { // Create one random Pet
		int n = rand.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public Pet[] createArray(int size) {
		Pet[] result = new Pet[size];
		for (int i = 0; i < size; i++)
			result[i] = randomPet();
		return result;
	}

	public ArrayList<Pet> arrayList(int size) {
		ArrayList<Pet> result = new ArrayList<Pet>();
		Collections.addAll(result, createArray(size));
		return result;
	}
}
