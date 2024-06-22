/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-22
 */
public class CRGWithBasicHolder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subtype t1 = new Subtype(), t2 = new Subtype();
		t1.set(t2);
		@SuppressWarnings("unused")
		Subtype t3 = t1.get();
		t1.f();
//		t3.f();
	}

}

class Subtype extends BasicHolder<Subtype> {}
