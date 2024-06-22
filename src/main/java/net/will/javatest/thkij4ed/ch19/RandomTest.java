/**
 * @(#)RandomTest.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch19;

/**
 * 
 *
 * @author Will
 * @version 2012-3-30
 */
public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 0; i < 20; i++)
			System.out.print(Enums.random(Activity.class) + " ");
	}

}

enum Activity {
	SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FALLING, FLYING
}
