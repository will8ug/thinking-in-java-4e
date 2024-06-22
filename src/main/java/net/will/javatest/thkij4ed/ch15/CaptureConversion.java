/**
 * 
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * @author Will
 * @version 2011-10-21
 */
public class CaptureConversion {
	static <T> void f1(Holder<T> holder) {
		T t = holder.get();
		System.out.println(t.getClass().getSimpleName());
	}
	
	static void f2(Holder<?> holder) {
		// you can't return T from f2(), because T is unknown for f2().
		f1(holder);    // call with captured type
	}

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })   // comment this line to see the warnings
	public static void main(String[] args) {
		Holder raw = new Holder<Integer>(1);
		f1(raw);   // produces warnings
		f2(raw);   // no warnings
		Holder rawBasic = new Holder();
		rawBasic.set(new Object());   // warnings
		f2(rawBasic);   // no warnings
		// upcast to Holder<?>, still figures it out:
		Holder<?> wildcarded = new Holder<Double>(1.0);
		f2(wildcarded);
	}

}
