/**
 * 
 */
package net.will.thkij4ed.ch17;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Map implemented with ArrayLists.
 * 
 * @author Will
 * @version 2012-10-17
 */
public class SlowMap<K, V> extends AbstractMap<K, V> {
	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();
	
	@Override
	public V put(K key, V value) {
		V oldValue = get(key); // The old value or null
		if (!keys.contains(key)) {
			keys.add(key);
			values.add(value);
		} else {
			values.set(keys.indexOf(key), value);
		}
		return oldValue;
	}
	
	@Override
	public V get(Object key) {   // key is type Object, not K
		if (!keys.contains(key)) {
			return null;
		}
		return values.get(keys.indexOf(key));
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		while (ki.hasNext()) {
			set.add(new MapEntry<K, V>(ki.next(), vi.next()));
		}
		return set;
	}

	public static void main(String[] args) {
		SlowMap<String,String> m= new SlowMap<String,String>();
		m.putAll(Countries.capitals(15));
		System.out.println(m);
		System.out.println(m.get("CONGO"));
		System.out.println(m.entrySet());
	}

}
