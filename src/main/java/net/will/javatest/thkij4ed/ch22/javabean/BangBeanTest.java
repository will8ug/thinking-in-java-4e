/**
 * @(#)BangBeanTest.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.javabean;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * {Timeout: 5} Abort after 5 seconds when testing
 * 
 * @author Will
 * @version 2012-4-18
 */
public class BangBeanTest extends JFrame {
	private static final long serialVersionUID = 2592625423060145521L;

	private JTextField txt = new JTextField(20);

	// During testing, report actions:
	class BBL implements ActionListener {
		private int count = 0;

		public void actionPerformed(ActionEvent e) {
			txt.setText("BangBean action " + count++);
		}
	}

	public BangBeanTest() {
		BangBean bb = new BangBean();
		try {
			bb.addActionListener(new BBL());
		} catch (TooManyListenersException e) {
			txt.setText("Too many listeners");
		}
		add(bb);
		add(BorderLayout.SOUTH, txt);
	}

	public static void main(String[] args) {
		run(new BangBeanTest(), 400, 500);
	}

}
