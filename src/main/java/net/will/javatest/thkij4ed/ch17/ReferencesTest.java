/**
 * 
 */
package net.will.javatest.thkij4ed.ch17;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

//import net.will.common.util.StaticMethod;

/**
 * @author Will
 * @version 2011-11-1
 */
public class ReferencesTest {
	private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

	public static void checkQueue() {
		Reference<? extends VeryBig> inq = rq.poll();
		if (inq != null) {
			System.out.println("In queue: " + inq.get());
		} else {
			System.out.println("inq in checkQueue() is null.");
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 10;
		// or, choose size via the command line:
//		if (args.length > 0) {
//			size = new Integer( StaticMethod.null2Int(args[0]) );
//		}
		
		LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			sa.add(new SoftReference<VeryBig>(new VeryBig("Soft"+i), rq));
			System.out.println("Just created: " + sa.getLast());
			checkQueue();
		}
		
		LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			wa.add(new WeakReference<VeryBig>(new VeryBig("Weak"+i), rq));
			System.out.println("Just created: " + wa.getLast());
			checkQueue();
		}
		
		@SuppressWarnings("unused")
		SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
		@SuppressWarnings("unused")
		WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));
		System.gc();
		
		LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
		for (int i = 0; i < size; i++) {
			pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom"+i), rq));
			System.out.println("Just created: " + pa.getLast());
			checkQueue();
		}
	}

}

class VeryBig {
	private static final int SIZE = 10000;
	private long[] la = new long[SIZE];
	private String ident;
	public VeryBig(String id) { ident = id; }
	public long[] getLa() { return la; }
	@Override
	public String toString() { return ident; }
	@Override
	protected void finalize() {
		System.out.println("Finalizing " + ident);
	}
}