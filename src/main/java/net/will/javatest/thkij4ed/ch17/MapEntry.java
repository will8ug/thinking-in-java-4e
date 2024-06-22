/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.Map.Entry;

/**
 * A simple Map.Entry for sample Map implementations.
 * 
 * @author Will
 * @version 2012-10-17
 * @param <K>
 * @param <V>
 */
public class MapEntry<K, V> implements Entry<K, V> {
	private K key;
	private V value;
	public MapEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V result = this.value;
		this.value = value;
		return result;
	}
	
	@Override
	public int hashCode() {
		return (key == null ? 0 : key.hashCode())
				^ (value == null ? 0 : value.hashCode());
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MapEntry))
			return false;
		MapEntry me = (MapEntry) o;
		return (key == null ?
				me.getKey() == null : key.equals(me.getKey())) &&
				(value == null ?
				me.getValue() == null : value.equals(me.getValue()));
	}
	
	@Override
	public String toString() {
		return key + "=" + value;
	}

}
