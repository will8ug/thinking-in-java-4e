/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Will
 * @version 2011-10-31
 */
public class Utilities {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<? super String> list = new ArrayList<String>();
		list.add("list01");
		list.add("list02");
		list.add("list03");
		list.add("list04");
		List<String> source = new LinkedList<String>();
		source.add("str01");
		source.add("str03");
		source.add("str02");
		
		// the destination list must be at least as long as the source list. 
		Collections.copy(list, source);
		System.out.println(list);
	}

}
