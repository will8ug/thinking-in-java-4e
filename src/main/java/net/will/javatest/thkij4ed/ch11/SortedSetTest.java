/**
 * 
 */
package net.will.javatest.thkij4ed.ch11;

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
public class SortedSetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		SortedSet<Integer> intset = new TreeSet<Integer>();
		Set<Integer> lhset = new LinkedHashSet<Integer>();
		Set<Integer> hsset = new HashSet<Integer>();
		for (int i = 0; i < 10; i++) {
			Integer o = new Integer(rand.nextInt(100));
			System.out.print(o + " | ");
			intset.add(o);
			lhset.add(o);
			hsset.add(o);
		}
		System.out.println();
		System.out.println(intset);
		System.out.println(lhset);
		System.out.println(hsset);
	}

}
