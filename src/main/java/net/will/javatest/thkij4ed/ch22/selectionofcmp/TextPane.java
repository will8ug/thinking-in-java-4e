/**
 * @(#)TextPane.java - Will's practices of Project javatest.
 */
package net.will.javatest.thkij4ed.ch22.selectionofcmp;

import static net.will.javatest.thkij4ed.ch22.SwingConsole.run;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import net.will.javatest.thkij4ed.ch15.Generator;
import net.will.javatest.thkij4ed.ch16.RandomGenerator;

/**
 * The JTextPane control is a little editor.
 *
 * @author Will
 * @version 2012-4-11
 */
public class TextPane extends JFrame {
	private static final long serialVersionUID = -816520825405145643L;

	private JButton b = new JButton("Add Text");
	private JTextPane tp = new JTextPane();
	@SuppressWarnings("rawtypes")
	private static Generator sg = new RandomGenerator.String(7);

	public TextPane() {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 1; i < 10; i++)
					tp.setText(tp.getText() + sg.next() + "\n");
			}
		});
		add(new JScrollPane(tp));
		add(BorderLayout.SOUTH, b);
	}

	public static void main(String[] args) {
		run(new TextPane(), 475, 425);
	}

}
