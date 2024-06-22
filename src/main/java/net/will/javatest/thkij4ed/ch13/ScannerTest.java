/**
 * 
 */
package net.will.javatest.thkij4ed.ch13;

import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * @author Will
 * @version 2011-10-14
 */
public class ScannerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test01();
		System.out.println();
		test02();
		System.out.println();
		test03();
	}
	
	public static void test01() {
		String input = "1 fish 2 fish red fish blue fish";
		Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
		System.out.println(s.nextInt());
		System.out.println(s.ioException());
		System.out.println(s.nextInt());
		System.out.println(s.ioException());
		System.out.println(s.next());
		System.out.println(s.next());
		s.close();
	}
	
	public static void test02() {
		String input = "1 fish 2 fish red fish blue fish";
		Scanner s = new Scanner(input);
		System.out.println("delimiter: " + s.delimiter());
		String r0 = s.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)");
		System.out.println(r0);
		System.out.println("delimiter: " + s.delimiter());
		MatchResult result = s.match();
		for (int i=1; i<=result.groupCount(); i++)
			System.out.println(result.group(i));
		s.close();
	}
	
	public static void test03() {
		String threatData = "58.27.82.161@02/10/2005\n" +
				"204.23.33.40@02/11/2005\n" +
				"58.27.82.101@02/11/2005\n" +
				"58.27.82.163@02/12/2005\n" +
				"[next log section]";
		Scanner scanner = new Scanner(threatData);
//		String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
		String pattern = "(\\d+.\\d+\\.\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
		while (scanner.hasNext(pattern)) {
			scanner.next(pattern);
			MatchResult match = scanner.match();
			String ip = match.group(1);
			String date = match.group(2);
			System.out.format("Threat on %s from %s\n", date, ip);
		}
	}

}
