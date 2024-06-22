/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * @author Will
 * @version 2011-9-10
 */
public class VarargsTest03 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		f(1, 'a');
//		f();           // 编译不通过
		f('a', 'b');
	}
	
	static void f(float i, Character... args) {
		System.out.println("first");
	}
	
	static void f(char c, Character... args) {
		System.out.println("second");
	}

}
