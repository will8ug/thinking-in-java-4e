/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-19
 */
public class ArrayOfGeneric {
	static final int SIZE = 100;
	static Generic<Integer>[] gia;

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// compiles; produces ClassCastException
//		gia = (Generic<Integer>[]) new Object[SIZE];
		// runtime type is the raw (erased) type:
		gia = (Generic<Integer>[]) new Generic[SIZE];
		System.out.println(gia.getClass().getSimpleName());   // Generic[]
		gia[0] = new Generic<Integer>();
//		gia[1] = new Object();   // compile-time error
		// discovers type mismatch at compile time:
//		gia[2] = new Generic<Double>();
	}

}

class Generic<T> {}
