/**
 * @(#)SimpleMenus.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * 
 * 
 * @author Will
 * @version 2012-4-12
 */
public class SimpleMenus extends JFrame {
	private static final long serialVersionUID = -9208938514444220362L;

	private JTextField t = new JTextField(15);
	private ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			t.setText(((JMenuItem) e.getSource()).getText());
		}
	};
	private JMenu[] menus = { new JMenu("Winken"), new JMenu("Blinken"),
			new JMenu("Nod") };
	private JMenuItem[] items = { new JMenuItem("Fee"), new JMenuItem("Fi"),
			new JMenuItem("Fo"), new JMenuItem("Zip"), new JMenuItem("Zap"),
			new JMenuItem("Zot"), new JMenuItem("Olly"), new JMenuItem("Oxen"),
			new JMenuItem("Free") };

	public SimpleMenus() {
		for (int i = 0; i < items.length; i++) {
			items[i].addActionListener(al);
			menus[i % 3].add(items[i]);
		}
		JMenuBar mb = new JMenuBar();
		for (JMenu jm : menus)
			mb.add(jm);
		setJMenuBar(mb);
		setLayout(new FlowLayout());
		add(t);
	}

	public static void main(String[] args) {
		run(new SimpleMenus(), 200, 150);
	}

}
