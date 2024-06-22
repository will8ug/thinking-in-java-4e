/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * @author Will
 * @version 2011-9-10
 */
public class VarargsTest02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		f(1, 'a');
		f();
//		f('a', 'b');    // 编译不通过(ambiguous)，编译器认为这个语句调用的第一个f方法。
	}
	
	static void f(float i, Character... args) {
		System.out.println("first");
	}
	
	static void f(Character... args) {
		System.out.println("second");
	}

}
