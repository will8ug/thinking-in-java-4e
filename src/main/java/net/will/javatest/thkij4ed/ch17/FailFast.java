/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Will
 * @version 2011-10-31
 */
public class FailFast {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<String> c = new ArrayList<String>();
		Iterator<String> it = c.iterator();
		c.add("added");
		try {
			it.next();               // fail-fast here
		} catch (ConcurrentModificationException e) {
			System.out.println(e);
		}
		
		// no fail-fast:
		System.out.println();
		CopyOnWriteArrayList<String> co = new CopyOnWriteArrayList<String>(
				"o p q one six".split(" "));
		Iterator<String> it2 = co.iterator();
		co.add("five");
		for (; it2.hasNext();) {
			String str = (String) it2.next();
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println(co);
	}

}
