/**
 * @(#)SwingConsole.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Tool for running Swing demos from the console, both applets and JFrames.
 *
 * @author Will
 * @version 2012-4-5
 */
public class SwingConsole {
	public static void run(final JFrame f, final int width, final int height) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				f.setTitle(f.getClass().getSimpleName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);
			}
			
		});
	}
}
