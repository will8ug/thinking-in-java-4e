/**
 * 
 */
package net.will.javatest.thkij4ed.selfadditional;

import net.will.javatest.thkij4ed.ch10.DotNew;

/**
 * @author Will
 * @version 2012-9-6
 */
public class TestInnerClass {

	public static void main(String[] args) {
		DotNew d = new DotNew();
		DotNew.Inner di = d.new Inner();
		di.funny();
		
		DotNewChild dnc = new DotNewChild();
		dnc.testInnerDefault();
	}

}

class DotNewChild extends DotNew {
	public void testInnerDefault() {
		InnerProtected ipt = new InnerProtected();
		ipt.funny();
	}
}
