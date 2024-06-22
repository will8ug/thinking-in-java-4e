/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-31
 */
public class ClassWithGenericTest<T> {
	private Class<T> clazz;
	
	public Class<T> getClazz() {
		return clazz;
	}

	private ClassWithGenericTest(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassWithGenericTest<String> c = new ClassWithGenericTest<String>(String.class);
		System.out.println(c.getClazz());   // class java.lang.String
		System.out.println(c.getClazz().isInstance("string object"));  // true
	}

}
