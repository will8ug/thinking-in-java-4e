/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import net.will.javatest.thkij4ed.common.CommonVariables;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

/**
 * Cooperate with People.java.
 *
 * @author Will
 * @version 2011-11-23
 */
public class Person {
	private static String outfile = CommonVariables.TEMP_OUTPUT_PATH + "people.xml";
	private String first, last;
	
	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	/**
	 * Constructor to restore a Person from an XML Element.
	 * @param person
	 */
	public Person(Element person) {
		first = person.getFirstChildElement("first").getValue();
		last = person.getFirstChildElement("last").getValue();
	}
	
	/**
	 * Produce an XML Element from this Person object.
	 * @return
	 */
	public Element getXML() {
		Element firstname = new Element("first");
		firstname.appendChild(first);
		Element lastname = new Element("last");
		lastname.appendChild(last);
		
		Element person = new Element("person");
		person.appendChild(firstname);
		person.appendChild(lastname);
		return person;
	}
	
	@Override
	public String toString() {
		return first + " " + last;
	}
	
	/**
	 * Make it human-readable.
	 * @param os
	 * @param doc
	 * @throws Exception
	 */
	public static void format(OutputStream os, Document doc) throws Exception {
		Serializer ser = new Serializer(os, "UTF-8");
		ser.setIndent(4);
		ser.setMaxLength(60);
		ser.write(doc);
		ser.flush();
	}
	
	public static void main(String[] args) throws Exception {
		List<Person> people = Arrays.asList(
				new Person("Dr. White", "Honey"),
				new Person("Gonzo", "Great"),
				new Person("三", "张"),       
				new Person("Phillip J.", "Fry"));
		System.out.println(people);
		
		Element root = new Element("people");
		for (Person p : people) {
			root.appendChild(p.getXML());
		}
		Document doc = new Document(root);
		format(System.out, doc);
		format(new BufferedOutputStream(new FileOutputStream(outfile)), doc);
	}

}
