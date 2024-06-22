/**
 * @(#)CollectionData.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.ArrayList;

import net.will.javatest.thkij4ed.ch15.Generator;

/**
 * A tool class.
 *
 * @author Will
 * @version 2012-4-1
 */
public class CollectionData<T> extends ArrayList<T> {
	private static final long serialVersionUID = -8010083109242103283L;

	public CollectionData(Generator<T> gen, int quantity) {
		for (int i = 0; i < quantity; i++)
			add(gen.next());
	}

	// A generic convenience method:
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
		return new CollectionData<T>(gen, quantity);
	}
}
