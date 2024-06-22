/**
 * @(#)CheckBoxes.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Using JCheckBoxes.
 *
 * @author Will
 * @version 2012-4-11
 */
public class CheckBoxes extends JFrame {
	private static final long serialVersionUID = -1324337690780508487L;

	private JTextArea t = new JTextArea(6, 15);
	private JCheckBox cb1 = new JCheckBox("Check Box 1"), cb2 = new JCheckBox(
			"Check Box 2"), cb3 = new JCheckBox("Check Box 3");

	public CheckBoxes() {
		cb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trace("1", cb1);
			}
		});
		cb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trace("2", cb2);
			}
		});
		cb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trace("3", cb3);
			}
		});
		setLayout(new FlowLayout());
		add(new JScrollPane(t));
		add(cb1);
		add(cb2);
		add(cb3);
	}

	private void trace(String b, JCheckBox cb) {
		if (cb.isSelected())
			t.append("Box " + b + " Set\n");
		else
			t.append("Box " + b + " Cleared\n");
	}

	public static void main(String[] args) {
		run(new CheckBoxes(), 200, 300);
	}

}
