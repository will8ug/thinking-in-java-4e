/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-19
 */
public class ErasureAndInheritance {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Derived2 d2 = new Derived2();
		Object obj = d2.get();
		// warning: Type safety: The method set(Object) belongs to the raw type GenericBase.
		//          References to generic type GenericBase<T> should be parameterized
		d2.set(obj);
	}

}

class GenericBase<T> {
	private T element;
	public void set(T arg) { element = arg; }
	public T get() {return element;}
}

class Derived1<T> extends GenericBase<T> {}

// warning: GenericBase is a raw type. References to generic type
//          GenericBase<T> should be parameterized
@SuppressWarnings("rawtypes")
class Derived2 extends GenericBase {}

// error: The type Derived3 cannot extend or implement GenericBase<?>.
//        A supertype may not specify any wildcard
//class Derived3 extends GenericBase<?> {}
