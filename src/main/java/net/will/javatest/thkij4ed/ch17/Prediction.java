/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.util.Random;

/**
 * 
 * @author Will
 * @version 2012-10-15
 */
public class Prediction {
	private static Random rand = new Random(47);
	
	private boolean shadow = rand.nextDouble() > 0.5;
	
	@Override
	public String toString() {
		if (shadow) {
			return "Six more weeks of Winter!";
		} else {
			return "Early Spring!";
		}
	}
}
