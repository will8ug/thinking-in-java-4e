/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * <p>
 * So, returning to BufferToText.java, if you rewind( ) the buffer 
 * (to go back to the beginning of the data) and then use that 
 * platform’s default character set to decode() the data, the resulting 
 * CharBuffer will print to the console just fine. To discover the 
 * default character set, use System.getProperty(“file.encoding"), 
 * which produces the string that names the character set. Passing 
 * this to Charset.forName() produces the Charset object that can 
 * be used to decode the string.
 * </p><p>
 * Another alternative is to encode( ) using a character set that 
 * will result in something printable when the file is read, as you 
 * see in the third part of BufferToText.java. Here, UTF-16BE is 
 * used to write the text into the file, and when it is read, all 
 * you must do is convert it to a CharBuffer, and it produces the 
 * expected text.
 * </p><p>
 * Finally, you see what happens if you write to the ByteBuffer 
 * through a CharBuffer (you’ll learn more about this later). 
 * Note that 24 bytes are allocated for the ByteBuffer. Since 
 * each char requires two bytes, this is enough for 12 chars, 
 * but "Some text" only has 9. The remaining zero bytes still 
 * appear in the representation of the CharBuffer produced by 
 * its toString( ), as you can see in the output.
 * </p>
 *
 * @author Will
 * @version 2014-07-06
 */
public class BufferToText {
	private static final int BSIZE = 1024;

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FileChannel fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		fc = new FileInputStream("data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// doesn't work:
		System.out.println(buff.asCharBuffer());
		
		// decode using this system's default Charset:
		buff.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoded using " + encoding +
				": " + Charset.forName(encoding).decode(buff));
		
		// or, we could encode with something that will print:
		fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
//		fc.write(ByteBuffer.wrap("Some text".getBytes("iso-8859-1")));
		fc.close();
		// now try reading again:
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
		
		// use a CharBuffer to write through:
		fc = new FileOutputStream("data2.txt").getChannel();
		buff = ByteBuffer.allocate(24);  // more than needed
		buff.asCharBuffer().put("Some text");
		fc.write(buff);
		fc.close();
		// read and display:
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
	}

}
