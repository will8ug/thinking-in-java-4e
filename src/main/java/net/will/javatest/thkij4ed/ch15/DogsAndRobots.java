/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-22
 */
public class DogsAndRobots {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PerformingDog d = new PerformingDog();
		Robot r = new Robot();
		Communicate.perform(d);
		Communicate.perform(r);
	}
}

interface Performs {
	void speak();
	void sit();
}

class PerformingDog implements Performs {
	public void reproduce() {}
	@Override
	public void sit() {
		System.out.println("Sitting");
	}
	@Override
	public void speak() {
		System.out.println("Woof!");
	}
}

class Robot implements Performs {
	public void oilChange() {}
	@Override
	public void sit() {
		System.out.println("Click!");
	}
	@Override
	public void speak() {
		System.out.println("Clank!");
	}
}

class Communicate {
	public static <T extends Performs> void perform(T performer) {
		performer.speak();
		performer.sit();
	}
}