/**
 * @(#)SubmitSwingProgram.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * An improved version of <code>SubmitLabelManipulationTask</code>.
 *
 * @author Will
 * @version 2012-4-5
 */
public class SubmitSwingProgram extends JFrame {
	private static final long serialVersionUID = -7427980255663125071L;
	
	JLabel label;

	public SubmitSwingProgram() {
		super("Hello Swing");
		label = new JLabel("A Label");
		add(label);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 100);
		setVisible(true);
	}

	static SubmitSwingProgram ssp;

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ssp = new SubmitSwingProgram();
			}
		});
		TimeUnit.SECONDS.sleep(1);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ssp.label.setText("Hey! This is Different!");
			}
		});
	}

}
