/**
 * @(#)LookAndFeelTest.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Selecting different looks & feels.
 * 
 * @author Will
 * @version 2012-4-12
 */
public class LookAndFeelTest extends JFrame {
	private static final long serialVersionUID = 6812162526787650689L;

	private String[] choices = "Eeny Meeny Minnie Mickey Moe Larry Curly"
			.split(" ");
	private Component[] samples = { new JButton("JButton"),
			new JTextField("JTextField"), new JLabel("JLabel"),
			new JCheckBox("JCheckBox"), new JRadioButton("Radio"),
			new JComboBox(choices), new JList(choices), };

	public LookAndFeelTest() {
		super("Look And Feel");
		setLayout(new FlowLayout());
		for (Component component : samples)
			add(component);
	}

	private static void usageError() {
		System.out.println("Usage:LookAndFeelTest [cross|system|motif]");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length == 0)
			usageError();
		if (args[0].equals("cross")) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("system")) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (args[0].equals("motif")) {
			try {
				UIManager.setLookAndFeel("com.sun.java."
						+ "swing.plaf.motif.MotifLookAndFeel");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			usageError();
		// Note the look & feel must be set before
		// any components are created.
		run(new LookAndFeelTest(), 300, 300);
	}
}
