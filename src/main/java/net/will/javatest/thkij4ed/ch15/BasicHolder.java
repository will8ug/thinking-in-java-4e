/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-22
 */
public class BasicHolder<T> {
	T element;
	
	void set(T arg) { element = arg; }
	
	T get() { return element; }
	
	void f() {
		System.out.println(element.getClass().getSimpleName());
	}

}
