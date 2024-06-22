/**
 * 
 */
package net.will.javatest.thkij4ed.ch19;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import net.will.javatest.thkij4ed.ch18.OSExecute;

/**
 * 
 *
 * @author Will
 * @version 2011-11-26
 */
public class Reflection {

	public static void main(String[] args) {
		Set<String> exMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		System.out.println("Explore.containsAll(Enum)?" + exMethods.containsAll(enumMethods));
		
		System.out.print("Explore.removeAll(Enum): ");
		exMethods.removeAll(enumMethods);
		System.out.println(exMethods);
		
		String targetPath = "target/classes/net/will/javatest/thkij4ed/ch19/Explore";
		OSExecute.command("javap " + targetPath);
	}
	
	public static Set<String> analyze(Class<?> enumClass) {
		System.out.println("============= Analyzing " + enumClass + "=============");
		
		System.out.println("Integerfaces: ");
		for (Type t : enumClass.getGenericInterfaces()) {
			System.out.println(t);
		}
		
		System.out.println("Base: " + enumClass.getSuperclass());
		
		System.out.println("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for (Method m : enumClass.getMethods()) {
			methods.add(m.getName());
		}
		System.out.println(methods);
		
		return methods;
	}

}

enum Explore { HERE, THERE }
