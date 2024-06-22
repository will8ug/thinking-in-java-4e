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
public class GenericsAndCovariance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// wildcards allow covariance:
		List<? extends Fruit> flist = new ArrayList<Apple>();
		// compile error: can't add any type of object:
//		flist.add(new Apple());
//		flist.add(new Fruit());
//		flist.add(new Object());
		flist.add(null);   // legal but uninteresting
		// we know that it returns at least Fruit:
		Fruit f = flist.get(0);
		System.out.println(f);
	}

}

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}
