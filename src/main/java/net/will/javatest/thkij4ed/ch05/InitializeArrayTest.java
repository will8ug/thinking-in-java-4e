/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * @author Will
 * @version 2011-9-12
 */
public class InitializeArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Other.f(new String[]{"one", "two", "three"});
//		Other.f({"one", "two", "three"});    // ERROR，这种语法只能用于数组被定义之处
		String[] ss = {"one", "two", "three"};
		Other.f(ss);
	}

}

class Other {
	public static void f(String[] args) {
		for (String s : args) {
			System.out.println(s);
		}
		System.out.println();
	}
}