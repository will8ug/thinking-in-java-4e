/**
 * @(#)BasicGenerator.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch15;

/**
 * 
 *
 * @author Will
 * @version 2012-3-21
 */
public class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;
	
	public BasicGenerator(Class<T> type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see net.will.javatest.thkij4ed.ch15.Generator#next()
	 */
	@Override
	public T next() {
		try {
			// assumes type is a public class:
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// produce a default generator given a type token:
	public static <T> Generator<T> create(Class<T> type) {
		return new BasicGenerator<T>(type);
	}

}
