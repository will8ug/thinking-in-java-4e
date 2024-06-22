/**
 * 
 */
package net.will.javatest.thkij4ed.ch19;

import java.util.Arrays;

/**
 * 
 *
 * @author Will
 * @version 2011-11-26
 */
public class UpcastEnum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Search[] vals = Search.values();
		System.out.println( Arrays.toString(vals) );
		
		Enum<Search> e = Search.HITHER;  // upcast
//		e.values();   // no values() in Enum
		for(Enum<?> en : e.getClass().getEnumConstants()) {
			System.out.println(en);
		}
		for(Enum<? extends Search> en : Search.YON.getClass().getEnumConstants()) {
			System.out.println(en);
		}
	}

}

enum Search { HITHER, YON }
