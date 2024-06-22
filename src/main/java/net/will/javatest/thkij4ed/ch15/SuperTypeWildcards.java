/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

import java.util.List;

/**
 * @author Will
 * @version 2011-10-21
 */
public class SuperTypeWildcards {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	static void writeTo(List<? super Apple> apples) {
		// The argument apples is a List of some type that is the base type
		// of Apple; thus you know that it is safe to add an Apple or a subtype
		// of Apple.
		apples.add(new Apple());
		apples.add(new Jonathan());
//		apples.add(new Fruit());  // ERROR
	}

}

//class Fruit {}
//class Apple extends Fruit {}
//class Jonathan extends Apple {}
//class Orange extends Fruit {}
