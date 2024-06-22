/**
 * @(#)Course.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch19;

/**
 * 
 *
 * @author Will
 * @version 2012-3-30
 */
public enum Course {
	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);
	
	private Food[] values;

	private Course(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}
}
