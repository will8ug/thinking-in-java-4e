/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * <p>Nesting a class within a scope.</p>
 * 
 * <p>The class <code>TrackingSlip</code> is nested inside the
 * scope of an if statement. This does not mean that the
 * <code>class</code> is conditionally created—it gets compiled
 * along with everything else.</p>
 *
 * @author Will
 * @version 2012-9-3
 */
public class Parcel6 {
	private void internalTracking(boolean b) {
		if (b) {
			class TrackingSlip {
				private String id;
				TrackingSlip(String s) {
					id = s;
				}
				String getSlip() { return id; }
			}
			TrackingSlip ts = new TrackingSlip("slip");
			System.out.println(ts.getSlip());
		}
		// can't use it here! out of scope
//		TrackingSlip ts = new TrackingSlip("x");
	}
	
	public void track() { internalTracking(true); }

	public static void main(String[] args) {
		Parcel6 p = new Parcel6();
		p.track();
	}

}
