/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Change the value of <code>byteArrLen</code> to see the different performance
 * between using BufferedInputStream/BufferedOutputStream and not using them.
 * Notice that the default buffer size of BufferedInputStream/BufferedOutputStream
 * in JDK1.6 is 8192.
 *
 * @author Will
 * @version 2011-11-15
 */
public class FileCopyTest {
	private static String oldfile = "g:\\1.rar";
	private static String newfile = "g:\\2.rar";
	private static int byteArrLen = 1024;       // KEY value to the result

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long tmr01 = System.nanoTime();
		test01();
		double d01 = System.nanoTime() - tmr01;
		
		long tmr02 = System.nanoTime();
		test02();
		double d02 = System.nanoTime() - tmr02;
		
		System.out.println("tmr01: " + d01/1.0e9);
		System.out.println("tmr02: " + d02/1.0e9);
	}
	
	/**
	 * Copy file test with BufferedInputStream/BufferedOutputStream.
	 */
	private static void test01() {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(oldfile));
			bos = new BufferedOutputStream(new FileOutputStream(newfile));
			byte[] buffer = new byte[byteArrLen];
			int b = 0;
			
			while ( (b = bis.read(buffer)) >= 0 ) {
				bos.write(buffer, 0, b);
			}
//			bos.flush();
//			bis.close();
//			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try { bis.close(); } catch (IOException ioe) {}
			}
			if (bos != null) {
				try { bos.close(); } catch (IOException ioe) {}
			}
		}
	}
	
	/**
	 * Copy file test not with BufferedInputStream/BufferedOutputStream.
	 */
	private static void test02() {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(oldfile);
			os = new FileOutputStream(newfile);
			byte[] buffer = new byte[byteArrLen];
			int b = 0;
			
			while ( (b = is.read(buffer)) >= 0 ) {
				os.write(buffer, 0, b);
			}
//			bos.flush();
//			bis.close();
//			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try { is.close(); } catch (IOException ioe) {}
			}
			if (os != null) {
				try { os.close(); } catch (IOException ioe) {}
			}
		}
	}
	
}
