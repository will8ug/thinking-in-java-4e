/**
 * @(#)ColorBoxes.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.concurrency;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A visual demonstration of threading.
 * 
 * @author Will
 * @version 2012-4-16
 */
public class ColorBoxes extends JFrame {
	private static final long serialVersionUID = 8878126668894106593L;

	private int grid = 12;
	private int pause = 50;
	private static ExecutorService exec = Executors.newCachedThreadPool();

	public void setUp() {
		setLayout(new GridLayout(grid, grid));
		for (int i = 0; i < grid * grid; i++) {
			CBox cb = new CBox(pause);
			add(cb);
			exec.execute(cb);
		}
	}

	public static void main(String[] args) {
		ColorBoxes boxes = new ColorBoxes();
		if (args.length > 0)
			boxes.grid = new Integer(args[0]);
		if (args.length > 1)
			boxes.pause = new Integer(args[1]);
		boxes.setUp();
		run(boxes, 500, 400);
	}

}

class CBox extends JPanel implements Runnable {
	private static final long serialVersionUID = 235219584929681555L;
	private int pause;
	private static Random rand = new Random();
	private Color color = new Color(0);

	public void paintComponent(Graphics g) {
		g.setColor(color);
		Dimension s = getSize();
		g.fillRect(0, 0, s.width, s.height);
	}

	public CBox(int pause) {
		this.pause = pause;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				color = new Color(rand.nextInt(0xFFFFFF));
				repaint(); // Asynchronously request a paint()
				TimeUnit.MILLISECONDS.sleep(pause);
			}
		} catch (InterruptedException e) {
			// Acceptable way to exit
		}
	}
}
