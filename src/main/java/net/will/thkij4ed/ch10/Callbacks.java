/**
 * 
 */
package net.will.thkij4ed.ch10;

/**
 * Using inner classes for callbacks.
 * 
 * @author Will
 * @version 2012-9-24
 */
public class Callbacks {
	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
		MyIncrement.f(c2);
		Caller caller1 = new Caller(c1);
		Caller caller2 = new Caller(c2.getCallbackReference());
		caller1.go();
		caller1.go();
		caller2.go();
		caller2.go();
	}
}

interface Incrementable {
	void increment();
}

//Very simple to just implement the interface:
class Callee1 implements Incrementable {
	private int i = 0;
	@Override
	public void increment() {
		i++;
		System.out.println(i);
	}
}

class MyIncrement {
	public void increment() { System.out.println("other operation"); }
	static void f(MyIncrement mi) { mi.increment(); }
}

//If your class must implement increment() in
//some other way, you must use an inner class:
class Callee2 extends MyIncrement {
	private int i = 0;
	@Override
	public void increment() {
		super.increment();
		i++;
		System.out.println(i);
	}
	private class Closure implements Incrementable {
		@Override
		public void increment() {
			Callee2.this.increment();
		}
	}
	Incrementable getCallbackReference() {
		return new Closure();
	}
}

class Caller {
	private Incrementable callbackReference;
	Caller(Incrementable cbh) { callbackReference = cbh; }
	void go() { callbackReference.increment(); }
}
