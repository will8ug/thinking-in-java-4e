/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-21
 */
@SuppressWarnings({"unchecked","unused","rawtypes"})
public class Wildcards {
	// raw argument:
	static void rawArgs(Holder holder, Object arg) {
		// just warning here, they are commented to avoid error in the later code.
		// warning: unchecked call to set(T) as a member of the raw type Holder
//		holder.set(arg);
//		holder.set(new Wildcards());   // same warning
		
		// can't do this: don't have any 'T':
//		T t = holder.get();
		
		// OK, but type information has been lost:
		Object obj = holder.get();
	}
	
	// similar to rawArgs(), but errors instead of warnings:
	static void unboundedArg(Holder<?> holder, Object arg) {
//		holder.set(arg);   // 	error
		// set (capture of ?) in Holder<capture of ?> cannot be applied to (Object)
//		holder.set(new Wildcards());   // same error
		
		// can't do this, don't have any 'T':
//		T t = holder.get();
		
		// OK, but type information has been lost:
		Object obj = holder.get();
	}
	
	static <T> T exact1(Holder<T> holder) {
		T t = holder.get();
		return t;
	}
	
	static <T> T exact2(Holder<T> holder, T arg) {
		holder.set(arg);
		T t = holder.get();
		return t;
	}
	
	static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
//		holder.set(arg);    // error
		T t = holder.get();
		return t;
	}
	
	static <T> void wildSupertype(Holder<? super T> holder, T arg) {
		holder.set(arg);
//		T t = holder.get();  // error
		
		// OK, but type information has been lost
		Object obj = holder.get();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Holder raw = new Holder<Long>();
		// or:
		raw = new Holder();
		Holder<Long> qualified = new Holder<Long>();
		Holder<?> unbounded = new Holder<Long>();
		Holder<? extends Long> bounded = new Holder<Long>();
		Long lng = new Long(1);
		
		rawArgs(raw, lng);
		rawArgs(qualified, lng);
		rawArgs(unbounded, lng);
		rawArgs(bounded, lng);
		
		unboundedArg(raw, lng);
		unboundedArg(qualified, lng);
		unboundedArg(unbounded, lng);
		unboundedArg(bounded, lng);
		
		Object r1 = exact1(raw);    // warning
		Long r2 = exact1(qualified);
		Object r3 = exact1(unbounded);   // must return Object
		Long r4 = exact1(bounded);
		System.out.println(r1 + "|" + r2 + "|" + r3 + "|" + r4);
	}
}

class Holder<T> {
	private T value;
	
	public Holder() {};
	
	public Holder(T val) {
		this.value = val;
	}
	
	public void set(T val) {
		this.value = val;
	}
	
	public T get() {
		return this.value;
	}
	
	public boolean equals(Object obj) {
		return value.equals(obj);
	}
}