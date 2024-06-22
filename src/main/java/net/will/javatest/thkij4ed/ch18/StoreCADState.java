/**
 * 
 */
package net.will.javatest.thkij4ed.ch18;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.will.javatest.thkij4ed.common.CommonVariables;

/**
 * Cooperate with RecoverCADState.java.
 *
 * @author Will
 * @version 2011-11-22
 */
public class StoreCADState {
	static String outfile = CommonVariables.TEMP_OUTPUT_PATH + "CADState.out";
	
	public static void main(String[] args) throws Exception {
		List<Class<? extends Shape>> shapeTypes = new ArrayList<Class<? extends Shape>>();
		shapeTypes.add(Circle.class);
		shapeTypes.add(Square.class);
		shapeTypes.add(Line.class);
		
		List<Shape> shapes = new ArrayList<Shape>();
		// make some shapes
		for (int i = 0; i < 10; i++) {
			shapes.add(Shape.randomFactory());
		}
		// set all the static colors to GREEN
		for (int i = 0; i < 10; i++) {
			shapes.get(i).setColor(Shape.GREEN);
		}
		
		// now serialize them
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outfile));
		out.writeObject(shapeTypes);
		Circle.serializeStaticState(out);
		Square.serializeStaticState(out);
		Line.serializeStaticState(out);
		out.writeObject(shapes);
		
		// display them
		System.out.println(shapes);
	}
}

abstract class Shape implements Serializable {
	private static final long serialVersionUID = -3622208059529710037L;
	public static final int RED = 1, BLUE = 2, GREEN = 3;
	private int x, y, z;
	private static Random rand = new Random(47);
	private static int counter = 0;
	public abstract void setColor(int newColor);
	public abstract int getColor();
	public Shape(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
//	public void serializeStaticState(ObjectOutputStream oos)
//			throws IOException {
//		oos.writeInt(getColor());
//	}
//	public void deserializeStaticState(ObjectInputStream ois)
//			throws IOException {
//		setColor(ois.readInt());
//	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder()
				.append(getClass())
				.append("color[")
				.append(getColor())
				.append("] x[")
				.append(x)
				.append("] y[")
				.append(y)
				.append("] z[")
				.append(z)
				.append("]\n");
		return s.toString();
	}
	public static Shape randomFactory() {
		int x = rand.nextInt(100);
		int y = rand.nextInt(100);
		int z = rand.nextInt(100);
		switch (counter++ % 3) {
		default:
		case 0: return new Circle(x, y, z);
		case 1: return new Square(x, y, z);
		case 2: return new Line(x, y, z);
		}
	}
}

class Circle extends Shape {
	private static final long serialVersionUID = 6847407987757424327L;
	private static int color = RED;
	public Circle(int x, int y, int z) {
		super(x, y, z);
	}
	@Override
	public void setColor(int newColor) {
		color = newColor;
	}
	@Override
	public int getColor() {
		return color;
	}
	public static void serializeStaticState(ObjectOutputStream oos)
			throws IOException {
		oos.writeInt(color);
	}
	public static void deserializeStaticState(ObjectInputStream ois)
			throws IOException {
		color = ois.readInt();
	}
}

class Square extends Shape {
	private static final long serialVersionUID = 5433290127041080986L;
	private static int color;
	public Square(int x, int y, int z) {
		super(x, y, z);
		color = RED;
	}
	@Override
	public void setColor(int newColor) {
		color = newColor;
	}
	@Override
	public int getColor() {
		return color;
	}
	public static void serializeStaticState(ObjectOutputStream oos)
			throws IOException {
		oos.writeInt(color);
	}
	public static void deserializeStaticState(ObjectInputStream ois)
			throws IOException {
		color = ois.readInt();
	}
}

class Line extends Shape {
	private static final long serialVersionUID = -8240153736315031042L;
	private static int color = RED;
	public Line(int x, int y, int z) {
		super(x, y, z);
	}
	@Override
	public void setColor(int newColor) {
		color = newColor;
	}
	@Override
	public int getColor() {
		return color;
	}
	public static void serializeStaticState(ObjectOutputStream oos)
			throws IOException {
		oos.writeInt(color);
	}
	public static void deserializeStaticState(ObjectInputStream ois)
			throws IOException {
		color = ois.readInt();
	}
}