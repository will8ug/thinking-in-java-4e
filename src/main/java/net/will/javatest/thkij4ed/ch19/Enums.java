/**
 * @(#)Enums.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch19;

import java.util.Random;

/**
 * 
 *
 * @author Will
 * @version 2012-3-30
 */
public class Enums {
	private static Random rand = new Random(47);
	
	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}
