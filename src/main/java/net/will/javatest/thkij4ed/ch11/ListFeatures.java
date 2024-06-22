/**
 * 
 */
package net.will.javatest.thkij4ed.ch11;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for &lt;T&gt; T[] List.toArray(T[] a). If the list fits in the 
 * specified array, it is returned therein. Otherwise, a new array is allocated
 * with the runtime type of the specified array and the size of this list.
 * 
 * @author Will
 * @version 2011-9-22
 */
public class ListFeatures {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strs = new ArrayList<String>();
		strs.add("string01");
		strs.add("string02");
		strs.add("string03");
		
		String[] arr = strs.toArray(new String[0]);
		System.out.println(strs);
		System.out.println(arr);
		System.out.println(arr.length);
		for (String string : arr) {
			System.out.print(string);
			System.out.print(" | ");
		}
		System.out.println();
		
		String[] arr2 = strs.<String>toArray(new String[5]);   // NOTICE
		System.out.println(arr2);
		System.out.println(arr2.length);
		for (String string : arr2) {
			System.out.print(string);
			System.out.print(" | ");
		}
		System.out.println();
	}

}
