/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>
 * If we try to use types that don't properly support the necessary operations with
 * Sets that require those operations, things go very wrong. Placing a SetType or
 * TreeType object, which doesn't include a redefined hashCode() method, into any
 * hashed implementations results in duplicate values, so the primary contract of
 * the Sets is violated.
 * </p>
 * <p>
 * In TreeType.compareTo(), note that I did <i>not</i> use the "simple and obvious"
 * form<code>return i-i2</code>. Although this is a common programming error, it
 * would only work properly if <code>i</code> and <code>i2</code> were "unsigned"
 * ints (if Java had an "unsigned" keyword, which it does not). It breaks for Java's
 * signed <code>int</code>, which is not big enough to represent the difference of
 * two signed ints. If <code>i</code> is a large positive integer and <code>j</code>
 * is a large negative integer, <code>i-j</code> will overflow and return a negative
 * value, which will not work.
 * </p>
 * 
 * @author Will
 * @version 2011-10-26
 */
public class Types4Sets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// correct code:
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(), HashType.class);
		test(new TreeSet<TreeType>(), TreeType.class);
		System.out.println();
		
		// things that don't work:
		test(new HashSet<SetType>(), SetType.class);    // error: duplicate elements
		test(new HashSet<TreeType>(), TreeType.class);  // error: duplicate elements
		test(new LinkedHashSet<SetType>(), SetType.class);    // error: duplicate elements
		test(new LinkedHashSet<TreeType>(), TreeType.class);  // error: duplicate elements
		try {
			test(new TreeSet<SetType>(), SetType.class);   // error: java.lang.ClassCastException
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			test(new TreeSet<HashType>(), HashType.class); // error: java.lang.ClassCastException
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	static <T> Set<T> fill(Set<T> set, Class<T> type) {
		try {
			for (int i = 0; i < 10; i++) {
				set.add( type.getConstructor(int.class).newInstance(i) );
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return set;
	}
	
	static <T> void test(Set<T> set, Class<T> type) {
		fill(set, type);
		fill(set, type);   // try to add duplicates
		fill(set, type);
		System.out.println(set);
	}

}

class SetType {
	int i;
	public SetType(int i) {
		this.i = i;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return o instanceof SetType && (i == ((SetType)o).i);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(i);
	}
}

class HashType extends SetType {
	public HashType(int i) {
		super(i);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType> {
	public TreeType(int i) {
		super(i);
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TreeType arg) {
		return (arg.i < i ? -1 : (arg.i == i ? 0 : 1));
	}
}