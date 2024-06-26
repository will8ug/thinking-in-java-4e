/**
 * @(#)SubmitLabelManipulationTask.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch22;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * <tt>SwingUtilities.invokeLater()</tt> puts a task on the event queue to be
 * (eventually) executed by the event dispatch thread.
 * 
 * @author Will
 * @version 2012-4-5
 */
public class SubmitLabelManipulationTask {
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Hello Swing");
		final JLabel label = new JLabel("A Label");
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);
		
		TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setText("Hey! This is Different!");
			}
		});
	}
}
