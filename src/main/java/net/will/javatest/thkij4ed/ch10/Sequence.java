/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * Holds a sequence of Objects.
 *
 * @author Will
 * @version 2012-8-29
 */
public class Sequence {
	private Object[] items;
	private int next = 0;
	public Sequence(int size) { items = new Object[size]; }
	public void add(Object x) {
		if (next < items.length) {
			items[next++] = x;
		}
	}
	
	private class SequenceSelector implements Selector {
		private int i = 0;

		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if (i < items.length) {
				i++;
			}
		}
		
	}
	
	public Selector selector() {
		return new SequenceSelector();
	}

	public static void main(String[] args) {
		Sequence seq = new Sequence(10);
		for (int i=0; i<10; i++) {
			seq.add(Integer.toString(i));
		}
		Selector selector = seq.selector();
		while (!selector.end()) {
			System.out.println(selector.current());
			selector.next();
		}
	}

}

interface Selector {
	boolean end();
	Object current();
	void next();
}
