/**
 * @(#)HTMLButton.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Putting HTML text on Swing components.
 * 
 * @author Will
 * @version 2012-4-12
 */
public class HTMLButton extends JFrame {
	private static final long serialVersionUID = -3269352208791651275L;

	private JButton b = new JButton("<html><b><font size=+2>"
			+ "<center>Hello!<br><i>Press me now!");

	public HTMLButton() {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add(new JLabel("<html>" + "<i><font size=+4>Kapow!"));
				// Force a re-layout to include the new label:
				validate();
			}
		});
		setLayout(new FlowLayout());
		add(b);
	}

	public static void main(String[] args) {
		run(new HTMLButton(), 200, 500);
	}

}
