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
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.will.javatest.thkij4ed.common.CommonVariables;

/**
 * 
 *
 * @author Will
 * @version 2011-11-17
 */
public class ZipCompress {
	private static String zipfile = CommonVariables.TEMP_OUTPUT_PATH + "test.zip";
	private static String[] srcfiles = {
		"src/main/java/net/will/javatest/thkij4ed/ch18/FileCopyTest.java",
		"src/main/java/net/will/javatest/thkij4ed/ch18/GZIPCompress.java",
		"src/main/java/net/will/javatest/thkij4ed/ch18/OSExecute.java",
		"src/main/java/net/will/javatest/thkij4ed/ch18/OSExecuteDemo.java",
		"src/main/java/net/will/javatest/thkij4ed/ch18/OSExecuteException.java",
		"src/main/java/net/will/javatest/thkij4ed/ch18/ZipCompress.java",
		"src/main/java/net/will/javatest/thkij4ed/ch18/cn_file_utf8.txt",
		"src/main/java/net/will/javatest/thkij4ed/ch18/cn_file_gb2312.txt"
	};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
//		test01();
		test02();
	}
	
	/**
	 * My resolution.
	 * @throws IOException
	 */
	static void test02() throws IOException {
		FileOutputStream f = new FileOutputStream(zipfile);
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(csum);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		
		zos.setComment("A test of Java Zipping");
		
		for (String arg : srcfiles) {
			System.out.println("Writing file " + arg);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(arg));
			zos.putNextEntry(new ZipEntry(arg));
			int c;
			while ( (c = in.read()) != -1 ) {
				out.write(c);
			}
			in.close();
			out.flush();
		}
		out.close();
		
		// checksum is valid only after the file has been closed!
		System.out.println("checksum: " + csum.getChecksum().getValue());
		
		// extract the files:
		System.out.println("Reading file");
		FileInputStream fi = new FileInputStream(zipfile);
		CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = null;
		
		ZipEntry ze;
		while ( (ze = in2.getNextEntry()) != null ) {
			System.out.println("Reading file " + ze);
			// A headache problem comes out while you handle files with
			// different charset here. When an unexpected GC occurs here,
			// the new BufferedReader object will be closed, then the in2
			// will be closed too!
			if (ze.getName().toUpperCase().indexOf("GB2312") >= 0) {
				br = new BufferedReader(new InputStreamReader(in2, "GB2312"));
			} else {
				br = new BufferedReader(new InputStreamReader(in2, "UTF-8"));
			}
			String x;
			while ( (x = br.readLine()) != null ) {
				bw.write(x);
				bw.newLine();
			}
			bw.flush();
			// the close() may be executed unexpected while a GC occurs,
			// uncomment the next line to see the exception
//			br.close();
		}
		if (1 == srcfiles.length) {
			System.out.println("checksum: " + csumi.getChecksum().getValue());
		}
		bw.close();
	}

	/**
	 * From the book.
	 * @throws IOException
	 */
	static void test01() throws IOException {
		FileOutputStream f = new FileOutputStream(zipfile);
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zos = new ZipOutputStream(csum);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		
		zos.setComment("A test of Java Zipping");  // no corresponding getComment(), though
		
		for (String arg : srcfiles) {
			System.out.println("Writing file " + arg);
			BufferedReader in = new BufferedReader(new FileReader(arg));
			zos.putNextEntry(new ZipEntry(arg));
			int c;
			while ( (c = in.read()) != -1 ) {
				out.write(c);  // read a character, but write a byte
			}
			in.close();
			out.flush();
		}
		out.close();
		
		// checksum valid only after the file has been closed!
		System.out.println("checksum: " + csum.getChecksum().getValue());
		
		// extract the files:
		System.out.println("Reading file");
		FileInputStream fi = new FileInputStream(zipfile);
		CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		BufferedInputStream bis = new BufferedInputStream(in2);
		
		ZipEntry ze;
		while ( (ze = in2.getNextEntry()) != null ) {
			System.out.println("Reading file " + ze);
			int x;
			while ( (x = bis.read()) != -1 ) {
				// the bytes are written as given, but they are not meaningful
				// until they are transformed to characters
				System.out.write(x);
			}
		}
		if (1 == srcfiles.length) {
			System.out.println("checksum: " + csumi.getChecksum().getValue());
		}
		bis.close();
	}
	
	/**
	 * Alternative way to open and read zip files.
	 * @throws IOException
	 */
	static void readWithZipFile() throws IOException {
		ZipFile zf = new ZipFile(zipfile);
		Enumeration<? extends ZipEntry> e = zf.entries();
		while (e.hasMoreElements()) {
			ZipEntry ze = e.nextElement();
			System.out.println("File: " + ze);
			// extract data
			
		}
	}
}
