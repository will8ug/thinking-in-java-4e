/**
 * 
 */
package net.will.thkij4ed.ch15;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Will
 * @version 2011-10-19
 */
public class GenericArrayWithTypeToken<T> {
	private T[] array;
	
	@SuppressWarnings("unchecked")
	public GenericArrayWithTypeToken(Class<T> type, int sz) {
		array = (T[]) Array.newInstance(type, sz);
	}
	
	public void put(int index, T item) {
		array[index] = item;
	}
	
	public T get(int index) {
		return array[index];
	}
	
	public T[] rep() {
		return array;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericArrayWithTypeToken<Integer> gai = 
			new GenericArrayWithTypeToken<Integer>(Integer.class, 5);
		Integer[] ia = gai.rep();
		System.out.println( ia + ": " + Arrays.toString(ia) );
	}

}
