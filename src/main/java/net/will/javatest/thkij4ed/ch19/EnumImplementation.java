/**
 * 
 */
package net.will.javatest.thkij4ed.ch19;

import java.util.Random;

import net.will.javatest.thkij4ed.ch15.Generator;

/**
 * 
 *
 * @author Will
 * @version 2011-11-26
 */
public class EnumImplementation {
	public static <T> void printNext(Generator<T> rg) {
		System.out.print(rg.next() + ",");
	}

	public static void main(String[] args) {
		// Choose any instance:
		CartoonCharacter cc = CartoonCharacter.BOB;
		for (int i = 0; i < 10; i++) {
			printNext(cc);
		}
		
		System.out.println(System.getProperty("line.separator")
				+ "==============================");
		for (int i = 0; i < 10; i++) {
			System.out.print(CartoonCharacter.staticNext() + ",");
		}
	}

}

enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	
	private static Random rand = new Random(47);

	/* (non-Javadoc)
	 * @see net.will.javatest.thkij4ed.ch15.Generator#next()
	 */
	@Override
	public CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
	
	public static CartoonCharacter staticNext() {
		return values()[rand.nextInt(values().length)];
	}
	
}