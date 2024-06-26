/**
 * @(#)ComboBoxes.java - Will's practices of Project javatest.
 */
package net.will.thkij4ed.ch22.selectionofcmp;

import static net.will.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 *
 * @author Will
 * @version 2012-4-11
 */
public class ComboBoxes extends JFrame {
	private static final long serialVersionUID = -3187185327903311491L;

	private String[] description = { "Ebullient", "Obtuse", "Recalcitrant",
			"Brilliant", "Somnescent", "Timorous", "Florid", "Putrescent" };
	private JTextField t = new JTextField(15);
	private JComboBox c = new JComboBox();
	private JButton b = new JButton("Add items");
	private int count = 0;

	public ComboBoxes() {
		for (int i = 0; i < 4; i++)
			c.addItem(description[count++]);
		t.setEditable(false);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count < description.length)
					c.addItem(description[count++]);
			}
		});
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setText("index: " + c.getSelectedIndex() + " "
						+ ((JComboBox) e.getSource()).getSelectedItem());
			}
		});
		setLayout(new FlowLayout());
		add(t);
		add(c);
		add(b);
	}

	public static void main(String[] args) {
		run(new ComboBoxes(), 200, 175);
	}

}
