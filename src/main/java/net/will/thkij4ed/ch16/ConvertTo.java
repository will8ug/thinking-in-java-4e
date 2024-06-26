/**
 * @(#)ConvertTo.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch16;

/**
 * This tool takes any array of primitive wrapper types and produces the
 * associated array of primitives.
 * 
 * @author Will
 * @version 2012-4-1
 */
public class ConvertTo {
	public static boolean[] primitive(Boolean[] in) {
		boolean[] result = new boolean[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i]; // Autounboxing
		return result;
	}

	public static char[] primitive(Character[] in) {
		char[] result = new char[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

	public static byte[] primitive(Byte[] in) {
		byte[] result = new byte[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

	public static short[] primitive(Short[] in) {
		short[] result = new short[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

	public static int[] primitive(Integer[] in) {
		int[] result = new int[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

	public static long[] primitive(Long[] in) {
		long[] result = new long[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

	public static float[] primitive(Float[] in) {
		float[] result = new float[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

	public static double[] primitive(Double[] in) {
		double[] result = new double[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}
}
