/**
 * 
 */
package net.will.javatest.thkij4ed.ch08;

/**
 * If you call a dynamically-bound method inside a constructor, the overridden definition
 * for that method is used. However, the effect of this call can be rather unexpected
 * because the overridden method will be called before the object is fully constructed.
 * This can conceal some difficult-to-find bugs.
 * 
 * @author Will
 * @version 2011-9-14
 */
public class PolymorphicMethodsInsideConstructors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RoundGlyph(5);
	}

}

class Glyph {
	void draw() {
		System.out.println("Glyph.draw()");
	}
	
	Glyph() {
		System.out.println("Glyph() before draw()");
		draw();   // ==KEY==
		System.out.println("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;
	
	RoundGlyph(int r) {
		radius = r;
		System.out.println("RoundGlyph.RoundGlyph(), radius = " + radius);
	}
	
	void draw() {
		System.out.println("RoundGlyph.draw(), radius = " + radius);
	}
}
