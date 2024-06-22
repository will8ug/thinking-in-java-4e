/**
 * @(#)MapData.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.LinkedHashMap;

import net.will.javatest.thkij4ed.ch15.Generator;

/**
 * A tool class.
 *
 * @author Will
 * @version 2012-4-1
 */
public class MapData<K, V> extends LinkedHashMap<K, V> {
	private static final long serialVersionUID = 5626516381201607632L;

	// A single Pair Generator:
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pair<K, V> p = gen.next();
			put(p.key, p.value);
		}
	}

	// Two separate Generators:
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}

	// A key Generator and a single value:
	public MapData(Generator<K> genK, V value, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), value);
		}
	}

	// An Iterable and a value Generator:
	public MapData(Iterable<K> genK, Generator<V> genV) {
		for (K key : genK) {
			put(key, genV.next());
		}
	}

	// An Iterable and a single value:
	public MapData(Iterable<K> genK, V value) {
		for (K key : genK) {
			put(key, value);
		}
	}

	// Generic convenience methods:
	public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen,
			int quantity) {
		return new MapData<K, V>(gen, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK,
			Generator<V> genV, int quantity) {
		return new MapData<K, V>(genK, genV, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, V value,
			int quantity) {
		return new MapData<K, V>(genK, value, quantity);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> genK, Generator<V> genV) {
		return new MapData<K, V>(genK, genV);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> genK, V value) {
		return new MapData<K, V>(genK, value);
	}
}
