/**
 * 
 */
package net.will.javatest.thkij4ed.ch11;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Test for the different behavior between TreeSet, LinkedHashSet and HashSet.
 * 
 * @author Will
 * @version 2011-9-23
 */
public class SortedSetTest4SelfObjects {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		SortedSet<SelfObject> intset = new TreeSet<SelfObject>(new Comparator<SelfObject>() {
			@Override
			public int compare(SelfObject o1, SelfObject o2) {
				return o1.getValue() - o2.getValue();
			}
		});
//		SortedSet<SelfObject> intset2 = new TreeSet<SelfObject>();
		Set<SelfObject> lhset = new LinkedHashSet<SelfObject>();
		Set<SelfObject> hsset = new HashSet<SelfObject>();
		for (int i = 0; i < 10; i++) {
			int j = rand.nextInt(100);
			SelfObject o = new SelfObject(j);
			System.out.print(j + " | ");
			intset.add(o);
			// java.lang.ClassCastException: net.will.javatest.thkij4ed.ch11.SelfObject cannot be cast to java.lang.Comparable
//			intset2.add(o);
			lhset.add(o);
			hsset.add(o);
		}
		System.out.println();
		System.out.println(intset);
//		System.out.println(intset2);
		System.out.println(lhset);
		System.out.println(hsset);
	}

}

class SelfObject {
	private int i;
	
	SelfObject(int i) {
		this.i = i;
	}
	
	int getValue() {
		return i;
	}
	
	public String toString() {
		return String.valueOf("SelfObject[" + i + "]");
	}
}
