/**
 * 
 */
package net.will.thkij4ed.ch14.pets;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Will
 * @version 2012-10-18
 */
public class ForNameCreator extends PetCreator {

	@Override
	public List<Class<? extends Pet>> types() {
		return types;
	}

	private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
	// Types that you want to be randomly created:
	private static String[] typeNames = { "typeinfo.pets.Mutt",
			"typeinfo.pets.Pug", "typeinfo.pets.EgyptianMau",
			"typeinfo.pets.Manx", "typeinfo.pets.Cymric", "typeinfo.pets.Rat",
			"typeinfo.pets.Mouse", "typeinfo.pets.Hamster" };

	@SuppressWarnings("unchecked")
	private static void loader() {
		try {
			for (String name : typeNames)
				types.add((Class<? extends Pet>) Class.forName(name));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	static {
		loader();
	}

}
