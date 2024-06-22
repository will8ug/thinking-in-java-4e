/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * A local inner class cannot have an access specifier because
 * it isn't part of the outer class, but it does have access to
 * the final variables in the current code block and all the
 * members of the enclosing class. Here's an example comparing
 * the creation of a local inner class with an anonymous inner
 * class.
 * 
 * @author Will
 * @version 2012-10-15
 */
public class LocalInnerClass {
	private int count = 0;
	
	Counter getCounter(final String name) {
		// a local inner class:
		class LocalCounter implements Counter {
			public LocalCounter() {
				// Local inner class can have a constructor
				System.out.println("LocalCounter()");
			}
			@Override
			public int next() {
				System.out.print(name); // access local final
				return count++;
			}
		}
		return new LocalCounter();
	}
	
	// The same thing with an anonymous inner class:
	Counter getCounter2(final String name) {
		return new Counter() {
			// Anonymous inner class cannot have a named
			// constructor, only an instance initializer:
			{
				System.out.println("Counter()");
			}
			@Override
			public int next() {
				System.out.print(name); // access local final
				return count++;
			}
		};
	}
	
	public static void main(String[] args) {
		LocalInnerClass lic = new LocalInnerClass();
		Counter c1 = lic.getCounter("Local inner ");
		Counter c2 = lic.getCounter2("Anonymous inner ");
		
		for (int i=0; i<5; i++) {
			System.out.println(c1.next());
		}
		for (int i=0; i<5; i++) {
			System.out.println(c2.next());
		}
	}
}

interface Counter {
	int next();
}
