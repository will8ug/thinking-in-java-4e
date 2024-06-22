/**
 * 
 */
package net.will.javatest.thkij4ed.ch05;

/**
 * @author Will
 * @version 2011-9-10
 */
public class VarargsTest01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		print(1, "one");
		print(2, "two", "three");
		print(0);  // 测试当这里不给可变参数列表传值时，print方法中是否会抛空指针异常
		
		f('a');
		f();
		g(1);
		g();
		System.out.println("int[]: " + new int[0].getClass());
	}

	public static void print(int required, String... trailing) {
		System.out.print("length: " + trailing.length + " | ");
		System.out.print("required: " + required + " | ");
		for (String s: trailing) {
			System.out.print(s + " ");
		}
		System.out.println();
//		String[] arr = new String[trailing.length]; // you can define an array with 0 size
//		System.out.println(Arrays.toString(arr));
	}
	
	public static void f(Character... args) {
		System.out.print(args.getClass());
//		System.out.print(" " + args.getClass().getName());
		System.out.println(" | length: " + args.length);
	}
	
	public static void g(int... args) {
		System.out.print(args.getClass());
//		System.out.print(" " + args.getClass().getName());
		System.out.println(" | length: " + args.length);
	}
}
