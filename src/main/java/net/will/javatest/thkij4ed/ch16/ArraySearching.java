/**
 * 
 */
package net.will.javatest.thkij4ed.ch16;

import java.util.Arrays;

/**
 * @author Will
 * @version 2011-10-23
 */
public class ArraySearching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = new int[] {4, 5, 23, 12, 79, 0};
		int[] b = new int[] {4, 5, 23, 12, 79, 0};
		System.out.println(Arrays.toString(a));
		
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		
		System.out.println(Arrays.binarySearch(a, 12));
		// if you try to use binarySearch() on an unsorted array, the
		// results will be unpredictable.
		System.out.println(Arrays.binarySearch(b, 12));
	}

}
