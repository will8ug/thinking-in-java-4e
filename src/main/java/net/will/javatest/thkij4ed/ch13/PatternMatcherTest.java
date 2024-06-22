/**
 * 
 */
package net.will.javatest.thkij4ed.ch13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author Will
 * @version 2011-10-13
 */
public class PatternMatcherTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test01();
		
		System.out.println("================");
		test02("abcabcabcdefabc", "abc+", "(abc)+", "(abc){2,}");
	}
	
	static void test01() {
		// This piece of test code is copied from java.util.regex.Matcher.appendReplacement()
		Pattern p = Pattern.compile("cat");
		Matcher m = p.matcher("one cat two cats in the yard");
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "dog");
		}
		m.appendTail(sb);
		System.out.println(sb.toString());
		
		// test for Matcher.group()
		Pattern p2 = Pattern.compile("a(b(c))");
		Matcher m2 = p2.matcher("bcabcdabc");
		while (m2.find()) {
			System.out.println("----------------");
			System.out.println("group(): " + m2.group());
			System.out.println("group(0): " + m2.group(0));
			System.out.println("group(1): " + m2.group(1));
			System.out.println("group(2): " + m2.group(2));
		}
	}
	
	static void test02(String... args) {
		if (args.length < 2) {
			return;
		}
		
		System.out.println("Input: \"" + args[0] + "\"");
		for (String arg : args) {
			System.out.println("----------------");
			System.out.println("Regular expression: \"" + arg + "\"");
			Pattern p = Pattern.compile(arg);
			Matcher m = p.matcher(args[0]);
			while (m.find()) {
				System.out.println("Match \"" + m.group() + "\" at positions " +
						m.start() + "-" + (m.end() - 1));
			}
		}
	}

}
