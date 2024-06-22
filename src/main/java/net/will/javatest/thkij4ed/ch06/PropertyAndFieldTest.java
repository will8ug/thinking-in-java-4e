/**
 * 
 */
package net.will.javatest.thkij4ed.ch06;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Notice the differences between the two outputs.
 * 
 * @author Will
 * @version 2011-10-29
 */
public class PropertyAndFieldTest {
	// both getter and setter property
	private String prop01;
	// only getter
	private String prop02;
	// only setter
	@SuppressWarnings("unused")
	private String prop03;
	// none
	@SuppressWarnings("unused")
	private String prop04;

	public String getProp01() {
		return prop01;
	}
	public void setProp01(String prop01) {
		this.prop01 = prop01;
	}
	
	public String getProp02() {
		return prop02;
	}
	
	public void setProp03(String prop03) {
		this.prop03 = prop03;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// OUTPUT 01:
		// fields: 
		// prop01 prop02 prop03 prop04 
		Field[] fields = PropertyAndFieldTest.class.getDeclaredFields();
		System.out.println("fields: ");
		for (Field f : fields) {
			String fieldName = f.getName();
			System.out.print(fieldName + " ");
		}
		System.out.println();

		// OUTPUT 02:
		// properties: 
		// class prop01 prop02 prop03 
		PropertyAndFieldTest t = new PropertyAndFieldTest();
		PropertyDescriptor[] propDescs = PropertyUtils.getPropertyDescriptors(t);
		System.out.println("properties: ");
		for (PropertyDescriptor ppDesc : propDescs) {
			String propName = ppDesc.getName();
			System.out.print(propName + " ");
		}
		System.out.println();
	}

}
