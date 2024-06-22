/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

import java.util.Comparator;

/**
 * 
 *
 * @author Will
 * @version 2012-2-22
 */
public class AnonymousInnerClassTest {
	public static void main(String[] args) {
		InterfaceOne obj = new InterfaceOne() {
			@Override
			public void eat() {
				System.out.println("method eat();");
			}
			@Override
			public void walk() {
				System.out.println("method walk();");
			}
			@Override
			public void talk() {
				System.out.println("method talk();");
			}
		};
		obj.eat();
		obj.walk();
		obj.talk();
		
		// NOTICE: there is no method <tt>equals(Object)</tt> in the following
		// anonymous inner class, because it is automatically derived from <tt>Object</tt>.
		Comparator<AnonymousInnerClassTest> cp = new Comparator<AnonymousInnerClassTest>() {
			@Override
			public int compare(AnonymousInnerClassTest o1,
					AnonymousInnerClassTest o2) {
				System.out.println("method compare();");
				return 0;
			}
		};
		cp.compare(new AnonymousInnerClassTest(), new AnonymousInnerClassTest());
	}
}
