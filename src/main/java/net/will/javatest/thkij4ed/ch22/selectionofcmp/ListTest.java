/**
 * @(#)ListTest.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * JLists do not automatically provide direct support for scrolling. Of course,
 * all you need to do is wrap the JList in a JScrollPane, and the details are
 * automatically managed for you.
 * 
 * @author Will
 * @version 2012-4-11
 */
public class ListTest extends JFrame {
	private static final long serialVersionUID = -3346551059717180661L;

	private String[] flavors = { "Chocolate", "Strawberry",
			"Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge",
			"Rum Raisin", "Praline Cream", "Mud Pie" };
	private DefaultListModel lItems = new DefaultListModel();
	private JList lst = new JList(lItems);
	private JTextArea t = new JTextArea(flavors.length, 20);
	private JButton b = new JButton("Add Item");
	private ActionListener bl = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (count < flavors.length) {
				lItems.add(0, flavors[count++]);
			} else {
				// Disable, since there are no more
				// flavors left to be added to the List
				b.setEnabled(false);
			}
		}
	};
	private ListSelectionListener ll = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting())
				return;
			t.setText("");
			for (Object item : lst.getSelectedValues())
				t.append(item + "\n");
		}
	};
	private int count = 0;

	public ListTest() {
		t.setEditable(false);
		setLayout(new FlowLayout());
		// Create Borders for components:
		Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
		lst.setBorder(brd);
		t.setBorder(brd);
		// Add the first four items to the List
		for (int i = 0; i < 4; i++)
			lItems.addElement(flavors[count++]);
		add(t);
		add(lst);
		add(b);
		// Register event listeners
		lst.addListSelectionListener(ll);
		b.addActionListener(bl);
	}

	public static void main(String[] args) {
		run(new ListTest(), 250, 375);
	}

}
