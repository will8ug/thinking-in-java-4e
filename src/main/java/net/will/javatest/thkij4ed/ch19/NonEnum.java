/**
 * 
 */
package net.will.javatest.thkij4ed.ch19;

/**
 * Test Class.getEnumConstants().
 *
 * @author Will
 * @version 2011-11-26
 */
public class NonEnum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class<Integer> intClass = Integer.class;
		try {
			for(Object en : intClass.getEnumConstants()) {
				System.out.println(en);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
