/**
 * 
 */
package net.will.javatest.thkij4ed.ch08;

/**
 * If a method is static, it doesn't behave polymorphically. Static methods are
 * associated with the class, and not the individual objects.
 * 
 * @author Will
 * @version 2011-9-14
 */
public class StaticMethod4PolymorphismTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		StaticSuper sup = new StaticSub();   // upcast
		System.out.println(sup.staticGet());   // ==KEY==
		System.out.println(sup.dynamicGet());
	}

}

class StaticSuper {
	public static String staticGet() {
		return "Base staticGet()";
	}
	
	public String dynamicGet() {
		return "Base dynamicGet()";
	}
}

class StaticSub extends StaticSuper {
	public static String staticGet() {
		return "Derived staticGet()";
	}
	
	public String dynamicGet() {
		return "Derived dynamicGet()";
	}
}