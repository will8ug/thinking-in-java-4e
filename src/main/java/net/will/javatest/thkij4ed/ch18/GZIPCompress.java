/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.will.javatest.thkij4ed.common.CommonVariables;

/**
 * 
 *
 * @author Will
 * @version 2011-11-16
 */
public class GZIPCompress {
	private static String scfile = "src/main/java/net/will/javatest/thkij4ed/ch18/css_gb2312.txt";
	private static String gzfile = CommonVariables.TEMP_OUTPUT_PATH + "test.gz";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
//		test01();
		test02();
//		test03();
	}

	/**
	 * One correct resolution.
	 * 
	 * @throws IOException
	 */
	static void test03() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(scfile), "GB2312"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new GZIPOutputStream(new FileOutputStream(gzfile))));
		
		String c;
		while( (c = in.readLine()) != null ) {
//			System.out.println(c);
			out.write(c);   // read as GB2312, and write as default charset(UTF-8)
			out.newLine();
		}
		in.close();
		out.close();
		
		System.out.println("Reading file");
		BufferedReader in2 = new BufferedReader(new InputStreamReader(
				new GZIPInputStream(new FileInputStream(gzfile))));
		String s;
		while( (s = in2.readLine()) != null ) {
			System.out.println(s);
		}
	}
	
	/**
	 * One correct resolution.
	 * 
	 * @throws IOException
	 */
	static void test02() throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(scfile));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream(gzfile)));
		
		int c;
		while( (c = in.read()) != -1 ) {
//			System.out.println(c);
			out.write(c);   // the charset of the source file won't be changed
		}
		in.close();
		out.close();
		
		System.out.println("Reading file");
		BufferedReader in2 = new BufferedReader(new InputStreamReader(
				new GZIPInputStream(new FileInputStream(gzfile)), "GB2312"));
		String s;
		while( (s = in2.readLine()) != null ) {
			System.out.println(s);
		}
	}
	
	/**
	 * The wrong code from the book.
	 * 
	 * @throws IOException
	 */
	static void test01() throws IOException {
		// use default charset here, but it could be wrong.
		BufferedReader in = new BufferedReader(new FileReader(scfile));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream(gzfile)));
		
		System.out.println("Writing file");
		int c;
		while ( (c = in.read()) != -1 ) {
			out.write(c);    // read a character but write a byte
		}
		in.close();
		out.close();
		
		System.out.println("Reading file");
		BufferedReader in2 = new BufferedReader(new InputStreamReader(
				new GZIPInputStream(new FileInputStream(gzfile))));
		String s;
		while( (s = in2.readLine()) != null ) {
			System.out.println(s);
		}
	}

}
