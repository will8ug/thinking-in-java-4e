/**
 * @(#)TabbedPane1.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Demonstrates the Tabbed Pane.
 *
 * @author Will
 * @version 2012-4-12
 */
public class TabbedPane1 extends JFrame {
	private static final long serialVersionUID = 3042920996596109751L;

	private String[] flavors = { "Chocolate", "Strawberry",
			"Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge",
			"Rum Raisin", "Praline Cream", "Mud Pie" };
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);

	public TabbedPane1() {
		int i = 0;
		for (String flavor : flavors) {
			tabs.addTab(flavor, new JButton("Tabbed pane " + i++));
		}
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " + tabs.getSelectedIndex());
			}
		});
		add(BorderLayout.SOUTH, txt);
		add(tabs);
	}

	public static void main(String[] args) {
		run(new TabbedPane1(), 400, 250);
	}

}
