/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.File;
import java.util.ArrayList;

import net.will.javatest.thkij4ed.common.CommonVariables;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

/**
 * Cooperate with Person.java.
 *
 * @author Will
 * @version 2011-11-23
 */
public class People extends ArrayList<Person> {
	private static final long serialVersionUID = 7393919777970008336L;
	
	private static String xmlfile = CommonVariables.TEMP_OUTPUT_PATH + "people.xml";
	
	public People(String filename) throws Exception {
		Document doc = new Builder().build(new File(filename));
		Elements elements = doc.getRootElement().getChildElements();
		for(int i = 0; i < elements.size(); i++) {
			add(new Person(elements.get(i)));
		}
	}
	
	public static void main(String[] args) throws Exception {
		People p = new People(xmlfile);
		System.out.println(p);
	}
}
