/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Will
 * @version 2011-10-19
 */
public class ArrayNewInstanceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Class.forName(SomeClass.class.getName());
		SomeClass[] arr01 = (SomeClass[])Array.newInstance(SomeClass.class, 5);
		System.out.println( arr01 + ": " + Arrays.toString(arr01) );
		
		ArrayMaker<String> stringm = new ArrayMaker<String>(String.class);
		String[] stringa = stringm.create(9);
		System.out.println( stringa + ": " + Arrays.toString(stringa) );
	}

}

class SomeClass {
	static int i = 0;
	int j;
	
	SomeClass() {
		j = i++;
	}
	
	public String toString() {
		return "SomeClass" + j;
	}
}

class ArrayMaker<T> {
	private Class<T> kind;
	public ArrayMaker(Class<T> kind) {
		this.kind = kind;
		System.out.println(kind.getName());    // java.lang.String
	}
	@SuppressWarnings("unchecked")
	T[] create(int size) {
		System.out.println(kind.getName());    // java.lang.String
		return (T[]) Array.newInstance(kind, size);
	}
}