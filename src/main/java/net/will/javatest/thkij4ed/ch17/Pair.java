/**
 * @(#)Pair.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch17;

/**
 * A tool class.
 *
 * @author Will
 * @version 2012-4-1
 */
public class Pair<K, V> {
	public final K key;
	public final V value;

	public Pair(K k, V v) {
		key = k;
		value = v;
	}
}
