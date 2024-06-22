/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;


/**
 * A demonstration hashed Map.
 * 
 * @author Will
 * @version 2012-10-18
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	// Choose a prime number for the hash table
	// size, to achieve a uniform distribution:
	static final int SIZE = 997;
	
	// You can’t have a physical array of generics,
	// but you can upcast to one:
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	
	@Override
	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if ( null == buckets[index] ) {
			buckets[index] = new LinkedList<MapEntry<K,V>>();
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		
		MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while (it.hasNext()) {
			MapEntry<K, V> ipair = it.next();
			if ( ipair.getKey().equals(key) ) {
				oldValue = ipair.getValue();
				it.set(pair);  // replace old with new
				found = true;
				break;
			}
		}
		
		if ( !found ) {
			buckets[index].add(pair);
		}
		
		return oldValue;
	}
	
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if ( null == buckets[index] ) {
			return null;
		}
		for ( MapEntry<K, V> ipair : buckets[index] ) {
			if ( ipair.getKey().equals(key) ) {
				return ipair.getValue();
			}
		}
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> set = new HashSet<Map.Entry<K,V>>();
		for ( LinkedList<MapEntry<K, V>> bucket : buckets ) {
			if ( null == bucket ) {
				continue;
			}
			
			for ( MapEntry<K, V> mpair : bucket ) {
				set.add(mpair);
			}
		}
		return set;
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String,String> m =
				new SimpleHashMap<String,String>();
		m.putAll(Countries.capitals(25));
		System.out.println(m);
		System.out.println(m.get("ERITREA"));
		System.out.println(m.entrySet());
	}

}
