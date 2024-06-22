/**
 * @(#)Generated.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch16;

import net.will.javatest.thkij4ed.ch15.Generator;
import net.will.javatest.thkij4ed.ch17.CollectionData;

/**
 * This tool produce an array of <tt>Object</tt> subtypes.
 *
 * @author Will
 * @version 2012-4-1
 */
public class Generated {
	// Fill an existing array:
	public static <T> T[] array(T[] a, Generator<T> gen) {
		return new CollectionData<T>(gen, a.length).toArray(a);
	}

	// Create a new array:
	@SuppressWarnings("unchecked")
	public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
		T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
		return new CollectionData<T>(gen, size).toArray(a);
	}
}
