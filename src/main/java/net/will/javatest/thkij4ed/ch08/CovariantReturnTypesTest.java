/**
 * 
 */
package net.will.javatest.thkij4ed.ch08;

/**
 * Java SE5 adds convariant return types, which means that an overridden method in a derived
 * class can return a type derived from the type returned by the base-class method. The key
 * difference between Java SE5 and earlier versions of java is that the earlier versions
 * would force the overridden version of process() to return Grain, rather than Wheat.
 * 
 * @author Will
 * @version 2011-9-14
 */
public class CovariantReturnTypesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Mill m = new Mill();
		Grain g = m.process();
		System.out.println(g);
		
		m = new WheatMill();
		g = m.process();
		System.out.println(g);
	}

}

class Grain {
	public String toString() {return "Grain";}
}

class Wheat extends Grain {
	public String toString() {return "Wheat";}
}

class Mill {
	Grain process() {
		return new Grain();
	}
}

class WheatMill extends Mill {
	Wheat process() {
		return new Wheat();
	}
}