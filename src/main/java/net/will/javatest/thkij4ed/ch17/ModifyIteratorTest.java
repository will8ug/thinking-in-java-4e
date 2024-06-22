/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Will
 * @version 2011-10-26
 */
public class ModifyIteratorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] arr = new String[] {"1", "2", "3"};
		
		List<String> a = new ArrayList<String>();
		for (String str : arr) {
			a.add(str);
		}
		testIterManipulation(a);
		
		a = new ArrayList<String>();
		for (String str : arr) {
			a.add(str);
		}
		testListIterManipulation(a);
	}
	
	public static void testIterManipulation(List<String> a) {
		Iterator<String> iter = a.iterator();
		try {
			iter.remove();
		} catch (Exception e) {
			System.out.println(e);    // java.lang.IllegalStateException
		}
		System.out.println(iter.next());
		iter.remove();
		System.out.println( a );
	}
	
	public static void testListIterManipulation(List<String> a) {
		System.out.println( a );
		ListIterator<String> lit = a.listIterator();
		System.out.println(lit.nextIndex() + ": " + lit.next());
		lit.add("4");
		System.out.println( a );
		
		try {
			lit.set("5");
		} catch (Exception e) {
			System.out.println(e);    // java.lang.IllegalStateException
		}
		lit.next();
		lit.set("5");
		System.out.println( a );
		
		try {
			lit.remove();             // it is ok now! (after set() is called)
			System.out.println( a );
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			lit.remove();
		} catch (Exception e) {
			System.out.println(e);    // java.lang.IllegalStateException
		}
		lit.previous();
		lit.remove();
		System.out.println( a );
	}

}
