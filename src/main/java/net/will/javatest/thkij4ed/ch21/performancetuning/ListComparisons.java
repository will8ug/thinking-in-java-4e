/**
 * @(#)ListComparisons.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch21.performancetuning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.will.javatest.thkij4ed.ch17.CountingIntegerList;

/**
 * <p>
 * From the output, you can see that a <tt>synchronized</tt> <tt>ArrayList</tt>
 * has roughly the same performance regardless of the number of readers and
 * writers—readers contend with other readers for locks in the same way that
 * writers do. The <tt>CopyOnWriteArrayList</tt>, however, is dramatically
 * faster when there are no writers, and is still significantly faster when
 * there are five writers. It would appear that you can be fairly liberal with
 * the use of <tt>CopyOnWriteArrayList</tt>; the impact of writing to the list
 * does not appear to overtake the impact of synchronizing the entire list for a
 * while. Of course, you must try the two different approaches in your specific
 * application to know for sure which one is best.
 * </p>
 * 
 * @author Will
 * @version 2012-4-1
 */
public class ListComparisons {
	public static void main(String[] args) {
		Tester.initMain(args);
		new SynchronizedArrayListTest(10, 0);
		new SynchronizedArrayListTest(9, 1);
		new SynchronizedArrayListTest(5, 5);
		new CopyOnWriteArrayListTest(10, 0);
		new CopyOnWriteArrayListTest(9, 1);
		new CopyOnWriteArrayListTest(5, 5);
		Tester.exec.shutdown();
	}
}

abstract class ListTest extends Tester<List<Integer>> {
	ListTest(String testId, int nReaders, int nWriters) {
		super(testId, nReaders, nWriters);
	}

	class Reader extends TestTask {
		long result = 0;

		void test() {
			for (long i = 0; i < testCycles; i++)
				for (int index = 0; index < containerSize; index++)
					result += testContainer.get(index);
		}

		void putResults() {
			readResult += result;
			readTime += duration;
		}
	}

	class Writer extends TestTask {
		void test() {
			for (long i = 0; i < testCycles; i++)
				for (int index = 0; index < containerSize; index++)
					testContainer.set(index, writeData[index]);
		}

		void putResults() {
			writeTime += duration;
		}
	}

	void startReadersAndWriters() {
		for (int i = 0; i < nReaders; i++)
			exec.execute(new Reader());
		for (int i = 0; i < nWriters; i++)
			exec.execute(new Writer());
	}
}

class SynchronizedArrayListTest extends ListTest {
	List<Integer> containerInitializer() {
		return Collections.synchronizedList(new ArrayList<Integer>(
				new CountingIntegerList(containerSize)));
	}

	SynchronizedArrayListTest(int nReaders, int nWriters) {
		super("Synched ArrayList", nReaders, nWriters);
	}
}

class CopyOnWriteArrayListTest extends ListTest {
	List<Integer> containerInitializer() {
		return new CopyOnWriteArrayList<Integer>(new CountingIntegerList(
				containerSize));
	}

	CopyOnWriteArrayListTest(int nReaders, int nWriters) {
		super("CopyOnWriteArrayList", nReaders, nWriters);
	}
}
