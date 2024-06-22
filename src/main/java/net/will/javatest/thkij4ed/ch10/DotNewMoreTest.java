/**
 * 
 */
package net.will.javatest.thkij4ed.ch10;

/**
 * @author Will
 * @version 2012-9-6
 */
public class DotNewMoreTest {
	public static void main(String[] args) {
		DotNew dn = new DotNew();
		DotNew.InnerDefault dnid = dn.new InnerDefault();
		dnid.funny();
	}
}
