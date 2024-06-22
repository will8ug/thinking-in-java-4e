/**
 * 
 */
package net.will.javatest.thkij4ed.ch03;

/**
 * 本例的重点在于熟悉如下知识点：
 * 如果对char，byte或者short类型的数值进行移位处理，那么在移位进行之前，它们会被转换为int类型，
 * 并且得到的结果也是一个int类型的值。
 * 
 * @author Will
 * @version 2011-9-4
 */
public class ShiftOperators {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 07777;
		System.out.println(Integer.toBinaryString(i) + " = " + i);
		i >>= 4;
		System.out.println(Integer.toBinaryString(i) + " = " + i);
		
		System.out.println();
		int j = -0xFF;
		System.out.println(Integer.toBinaryString(j) + " = " + j);
		j >>= 4;
		System.out.println(Integer.toBinaryString(j) + " = " + j);
		
		System.out.println();
		int k = -1;
		System.out.println(Integer.toBinaryString(k) + " = " + k);
		k >>= 1;
		System.out.println(Integer.toBinaryString(k) + " = " + k);
		
		/* 本例的重点在下面两次无符号右移位运算的区别。
		 * 第一次实际上是个错误的结果；
		 * 第二次结果应该是想要的结果，但由于右移了17位，实际上已经超出short类型数的最大位数；
		 */
		System.out.println();
		short p = -1;
		System.out.println(Integer.toBinaryString(p) + " = " + p);
		// 右移后不马上赋值给p，看看正常的结果
		System.out.println(Integer.toBinaryString(p>>>16) + " = " + (p>>>16));
		p >>>= 16;
		System.out.println(Integer.toBinaryString(p) + " = " + p);
		
		System.out.println();
		short q = -1;
		System.out.println(Integer.toBinaryString(q) + " = " + q);
		q >>>= 17;
		System.out.println(Integer.toBinaryString(q) + " = " + q);
	}

}
