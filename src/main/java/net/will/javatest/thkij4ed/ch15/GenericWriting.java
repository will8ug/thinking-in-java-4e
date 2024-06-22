/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Will
 * @version 2011-10-21
 */
public class GenericWriting {
	static <T> void writeExact(List<T> list, T item) {
		list.add(item);
	}
	
	static List<Apple> apples = new ArrayList<Apple>();
	static List<Fruit> fruits = new ArrayList<Fruit>();
	
	static void f1() {
		writeExact(apples, new Apple());
		writeExact(fruits, new Apple());
	}
	
	static <T> void writeWithWildcard(List<? super T> list, T item) {
		list.add(item);
	}
	
	static void f2() {
		writeWithWildcard(apples, new Apple());
		writeWithWildcard(fruits, new Apple());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		f1();
		f2();
		System.out.println( apples );
		System.out.println( fruits );
	}

}
