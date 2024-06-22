/**
 * 
 */
package net.will.javatest.thkij4ed.ch08;

/**
 * When a Sub object is upcast to a Super reference, any field accesses are resolved
 * by the compiler, and are thus not polymorphic.
 * 
 * @author Will
 * @version 2011-9-14
 */
public class Field4PolymorphismTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Super sup = new Sub();   // upcast
		System.out.println("sup.field=" + sup.field);   // ==KEY==
		System.out.println("sup.getField()=" + sup.getField());
	}

}

class Super {
	public int field = 0;
	public int getField() {return field;}
}

class Sub extends Super {
	public int field = 1;
	public int getField() {return field;}
	public int getSuperField() {return super.field;}
}
