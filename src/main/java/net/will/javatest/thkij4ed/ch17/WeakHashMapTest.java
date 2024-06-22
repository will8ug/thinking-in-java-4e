/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.WeakHashMap;

/**
 * When you run this program, you'll see that the garbage collector will skip
 * every third key, because an ordinary reference to that key has also been
 * placed in the <code>keys</code> array, and thus those objects cannot be
 * garbage collected.
 * 
 * @author Will
 * @version 2011-11-1
 */
public class WeakHashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 1000;
		Key[] keys = new Key[size];
		WeakHashMap<Key, Value> map = new WeakHashMap<Key, Value>();
		for (int i = 0; i < size; i++) {
			Key k = new Key(Integer.toString(i));
			Value v = new Value(Integer.toString(i));
			if (i % 3 == 0) {
				keys[i] = k;   // save as "real" references
			}
			map.put(k, v);
		}
		System.gc();
	}

}

class Element {
	private String ident;
	public Element(String id) { ident = id; }
	public String toString() { return ident; }
	public int hashCode() { return ident.hashCode(); }
	public boolean equals(Object r) {
		return r instanceof Element && ident.equals(((Element) r).ident);
	}
	protected void finalize() {
		System.out.println("finalizing " + getClass().getSimpleName() + " " + ident);
	}
}

class Key extends Element {
	public Key(String id) { super(id); }
}

class Value extends Element {
	public Value(String id) { super(id); }
}