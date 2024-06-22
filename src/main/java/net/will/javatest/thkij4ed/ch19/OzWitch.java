/**
 * 
 */
package net.will.javatest.thkij4ed.ch19;

/**
 * 
 *
 * @author Will
 * @version 2011-11-27
 */
public enum OzWitch {
	// instances must be defined first, before methods:
	WEST("WEST String"),
	NORTH("NORTH String"),
	EAST("EAST String"),
	SOUTH("SOUTH String");
	
	private String description;
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	// constructor must be package or private access:
	private OzWitch(String desc) {
		description =  desc;
	}
	
	public static void main(String[] args) {
		for (OzWitch witch : OzWitch.values()) {
			System.out.println(witch + ": " + witch.getDescription());
		}
	}
}
