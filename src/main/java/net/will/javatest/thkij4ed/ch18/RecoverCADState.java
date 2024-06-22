/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * The <code>statics</code> won't get serialized automatically - even though
 * class <code>Class</code> is <code>Serializable</code>, it doesn't do what
 * you expect. So if you want to serialize <code>statics</code>, you must do
 * it yourself.
 * </p>
 * <p>
 * Cooperate with StoreCADState.java.
 * </p>
 *
 * @author Will
 * @version 2011-11-22
 */
public class RecoverCADState {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				StoreCADState.outfile));
		List<Class<? extends Shape>> shapeTypes =
				(List<Class<? extends Shape>>) in.readObject();
		
		System.out.println(shapeTypes);
		// show staic fields first:
		for (Class<? extends Shape> c : shapeTypes) {
			try {
				Field field = c.getDeclaredField("color");
				if ( !field.isAccessible() ) {
					field.setAccessible(true);
				}
				System.out.println(c.getSimpleName() + ": " + field.get(c));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		
		Circle.deserializeStaticState(in);
		Square.deserializeStaticState(in);
		Line.deserializeStaticState(in);
		
		List<Shape> shapes = (List<Shape>) in.readObject();
		System.out.println(shapes);
	}

}
