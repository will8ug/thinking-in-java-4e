/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>Creating an inner class directly using the .new syntax.</p>
 * 
 * <p>To create an object of the inner class directly, you don't follow
 * the same form and refer to the outer class name <code>DotNew</code>
 * as you might expect, but instead you must use an object of the outer
 * class to make an object of the inner class, as you can see here.
 * This also resolves the name scoping issues for the inner class, so
 * you don't say (indeed, you can't say) <code>dn.new
 * DotNew.Inner()</code>.</p>
 *
 * @author Will
 * @version 2012-8-29
 */
public class DotNew {
	public class Inner {
		public void funny() {
			System.out.println("[Inner] I'm here!");
		}
	}
	
	// for a test outside of this class
	class InnerDefault {
		public void funny() {
			System.out.println("[InnerDefault] I'm here!");
		}
	}
	
	// for a test outside of this package
	protected class InnerProtected {
		// the constructor must be public to access it outside this package
		public InnerProtected() {}
		public void funny() {
			System.out.println("[InnerProtected] I'm here!");
		}
	}
	
	private class InnerPrivate {
		public void funny() {
			System.out.println("[InnerPrivate] I'm here!");
		}
	}

	public static void main(String[] args) {
		DotNew dn = new DotNew();
//		DotNew.Inner dni = dn.new DotNew.Inner();  // illegal
//		DotNew.Inner dnie = new DotNew.Inner();  // illegal
		DotNew.Inner dni = dn.new Inner();
		dni.funny();
		
		DotNew.InnerPrivate dnip = dn.new InnerPrivate();
		dnip.funny();
	}

}
