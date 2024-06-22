/**
 * @(#)Dialogs.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Creating and using Dialog Boxes.
 * 
 * @author Will
 * @version 2012-4-12
 */
public class Dialogs extends JFrame {
	private static final long serialVersionUID = -865527827041028627L;

	private JButton b1 = new JButton("Dialog Box");
	private MyDialog dlg = new MyDialog(null);

	public Dialogs() {
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlg.setVisible(true);
			}
		});
		add(b1);
	}

	public static void main(String[] args) {
		run(new Dialogs(), 125, 75);
	}

}

class MyDialog extends JDialog {
	private static final long serialVersionUID = -8406984853376262964L;

	public MyDialog(JFrame parent) {
		super(parent, "My dialog", true);
		setLayout(new FlowLayout());
		add(new JLabel("Here is my dialog"));
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Closes the dialog
			}
		});
		add(ok);
		setSize(150, 125);
	}
}
